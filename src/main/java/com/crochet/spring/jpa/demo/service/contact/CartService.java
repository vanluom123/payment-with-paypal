package com.crochet.spring.jpa.demo.service.contact;

public interface CartService {
    String addProductToCart(String customerId, String productId, int quantity);

    String placeOrder_2(String cusId);

    String placeOrder(String customerId);
}
