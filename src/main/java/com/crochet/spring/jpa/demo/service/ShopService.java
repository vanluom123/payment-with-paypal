package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.model.Shop;
import com.crochet.spring.jpa.demo.payload.dto.ghn.store.CreateOrUpdateShopRequest;

import java.util.List;
import java.util.UUID;

public interface ShopService {
    String createOrUpdate(CreateOrUpdateShopRequest request);

    List<Shop> getAll();

    Shop getById(UUID shopId);
}
