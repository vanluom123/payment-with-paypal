package com.crochet.spring.jpa.demo.service.ghn;

import com.crochet.spring.jpa.demo.payload.dto.ghn.GetShopsRequestDTO;
import com.crochet.spring.jpa.demo.properties.GHNProperties;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiaofeng.webclient.service.WebClientService;
import org.xiaofeng.webclient.type.HttpMethod;

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
    public String getShopAll(GetShopsRequestDTO getShopsRequest) {
        String url = "/shiip/public-api/v2/shop/all";
        String payload = gson.toJson(getShopsRequest);
        String result = clientService.invokeApi(url, HttpMethod.POST, payload).block();
        return result;
    }
}
