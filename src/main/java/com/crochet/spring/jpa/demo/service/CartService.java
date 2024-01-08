package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.model.Cart;
import com.crochet.spring.jpa.demo.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CartService {
    String addProductToCart(UUID customerId, UUID productId, int quantity);

    List<Cart> getAllCartByCustomer(Customer customer);

    void deleteAll(List<Cart> carts);

    Integer getQuantityByProductId(String productId);
}
