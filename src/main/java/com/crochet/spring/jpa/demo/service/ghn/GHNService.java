package com.crochet.spring.jpa.demo.service.ghn;

import com.crochet.spring.jpa.demo.dto.ghn.order.GHNOrderCreationRequest;
import com.crochet.spring.jpa.demo.dto.ghn.store.GHNShopRetrievalRequest;

import java.util.Map;

public interface GHNService {
    String getShopAll(GHNShopRetrievalRequest getShopsRequest);

    String getProvince();

    String getDistrict(String provinceId);

    String getWard(String id);

    String createOrder(GHNOrderCreationRequest request);

    Map<String, String> getAddressFromShop(GHNShopRetrievalRequest getShopsRequest);
}
