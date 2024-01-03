package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.payload.dto.ghn.shop.CreateOrUpdateShopRequest;

public interface ShopService {
    String createOrUpdate(CreateOrUpdateShopRequest request);
}
