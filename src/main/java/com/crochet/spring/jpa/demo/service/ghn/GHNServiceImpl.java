package com.crochet.spring.jpa.demo.service.ghn;

import com.crochet.spring.jpa.demo.dto.ghn.address.GHNDistrictDTO;
import com.crochet.spring.jpa.demo.dto.ghn.address.GHNGetWardDTO;
import com.crochet.spring.jpa.demo.dto.ghn.address.GHNProvinceDTO;
import com.crochet.spring.jpa.demo.dto.ghn.order.GHNOrderCreationRequest;
import com.crochet.spring.jpa.demo.dto.ghn.store.GHNShopResponse;
import com.crochet.spring.jpa.demo.dto.ghn.store.GHNShopRetrievalRequest;
import com.crochet.spring.jpa.demo.properties.GHNProperties;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiaofeng.webclient.service.WebClientService;
import org.xiaofeng.webclient.type.HttpMethod;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GHNServiceImpl implements GHNService {
    @Autowired
    private WebClientService clientService;
    @Autowired
    private GHNProperties GHNProps;
    @Autowired
    private Gson gson;

    @PostConstruct
    public void init() {
        clientService.builder()
                .baseUrl("https://dev-online-gateway.ghn.vn")
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add("Token", GHNProps.getToken());
                    httpHeaders.add("ShopId", GHNProps.getShopId());
                    httpHeaders.add("Content-Type", "application/json");
                });
    }

    @Override
    public String getShopAll(GHNShopRetrievalRequest getShopsRequest) {
        String url = "/shiip/public-api/v2/shop/all";
        String payload = gson.toJson(getShopsRequest);
        String result = clientService.invokeApi(url, HttpMethod.POST, payload).block();
        return result;
    }

    @Override
    public String getProvince() {
        String url = "/shiip/public-api/master-data/province";
        var result = clientService.invokeApi(url, HttpMethod.GET).block();
        return result;
    }

    @Override
    public String getDistrict(String provinceId) {
        String url = "/shiip/public-api/master-data/district?province_id=" + provinceId;
        var result = clientService.invokeApi(url, HttpMethod.GET).block();
        return result;
    }

    @Override
    public String getWard(String id) {
        String url = "/shiip/public-api/master-data/ward?district_id=" + id;
        var result = clientService.invokeApi(url, HttpMethod.GET).block();
        return result;
    }

    @Override
    public String createOrder(GHNOrderCreationRequest request) {
        String url = "/shiip/public-api/v2/shipping-order/create";
        var payload = gson.toJson(request);
        String result = clientService.invokeApi(url,
                HttpMethod.POST,
                payload).block();
        return result;
    }

    @Override
    public Map<String, String> getAddressFromShop(GHNShopRetrievalRequest getShopsRequest) {
        Map<String, String> data = new ConcurrentHashMap<>();

        var shopJsonString = this.getShopAll(getShopsRequest);
        var getShop = gson.fromJson(shopJsonString, GHNShopResponse.class);
        var shops = getShop.getData().getShops()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cannot found shop"));
        data.put("from_name", shops.getName());
        data.put("from_phone", shops.getPhone());
        data.put("from_address", shops.getAddress());

        var provinceJsonString = this.getProvince();
        var getProvince = gson.fromJson(provinceJsonString, GHNProvinceDTO.class);
        var provinces = getProvince.getData();

        provinces.parallelStream().forEach(province -> {
            var districtJsonString = this.getDistrict(String.valueOf(province.getProvinceID()));
            var getDistrict = gson.fromJson(districtJsonString, GHNDistrictDTO.class);
            var districts = getDistrict.getData();

            districts.parallelStream().forEach(district -> {
                if (district.getDistrictID() == shops.getDistrictId()) {
                    data.put("from_province_name", province.getProvinceName());
                    data.put("from_district_name", district.getDistrictName());

                    var wardJsonString = this.getWard(String.valueOf(district.getDistrictID()));
                    var getWard = gson.fromJson(wardJsonString, GHNGetWardDTO.class);
                    var wards = getWard.getData();

                    wards.parallelStream().filter(ward ->
                                    String.valueOf(ward.getWardCode()).equals(shops.getWardCode()))
                            .findFirst()
                            .ifPresent(ward -> data.put("from_ward_name", ward.getWardName()));
                }
            });
        });

        return data;
    }
}
