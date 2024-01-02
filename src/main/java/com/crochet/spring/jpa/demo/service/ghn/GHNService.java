package com.crochet.spring.jpa.demo.service.ghn;

import com.crochet.spring.jpa.demo.payload.dto.ghn.order.GHNCreateOrderRequest;
import com.crochet.spring.jpa.demo.payload.dto.ghn.store.GHNGetShopsRequest;

import java.util.Map;

public interface GHNService {
    String getShopAll(GHNGetShopsRequest getShopsRequest);

    String getProvince();

    String getDistrict(String provinceId);

    String getWard(String id);

    String createOrder(GHNCreateOrderRequest request);

    Map<String, String> getAddressFromShop(GHNGetShopsRequest getShopsRequest);
}
