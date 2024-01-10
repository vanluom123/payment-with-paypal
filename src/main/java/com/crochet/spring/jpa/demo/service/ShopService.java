package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.dto.ghn.store.GHNShopCreationRequest;
import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.model.Shop;

import java.util.List;
import java.util.UUID;

public interface ShopService {
    String createOrUpdate(GHNShopCreationRequest request);

    List<Shop> getAll();

    Shop getById(UUID shopId);

    Shop findShopForProducts(Customer customer);
}
