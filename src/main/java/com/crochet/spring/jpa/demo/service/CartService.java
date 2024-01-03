package com.crochet.spring.jpa.demo.service;

import java.util.UUID;

public interface CartService {
    String addProductToCart(UUID customerId, UUID productId, int quantity);

    String placeOrder_2(UUID cusId);

    String placeOrder(UUID customerId);
}
