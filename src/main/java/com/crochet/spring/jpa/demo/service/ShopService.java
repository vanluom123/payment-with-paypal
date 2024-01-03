package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.payload.dto.ghn.store.CreateOrUpdateShopRequest;

public interface ShopService {
    String createOrUpdate(CreateOrUpdateShopRequest request);
}
