package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.model.Cart;
import com.crochet.spring.jpa.demo.model.Customer;

import java.util.UUID;

public interface CartService {
  String addProductToCart(UUID customerId, UUID productId, int quantity);

  Cart getCartByCustomer(Customer customer);

  void deleteCart(Cart cart);
}
