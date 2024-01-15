package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.common.MessageConstant;
import com.crochet.spring.jpa.demo.model.Cart;
import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.repository.CartRepo;
import com.crochet.spring.jpa.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
  @Autowired
  private CartRepo cartRepo;

  @Override
  public String addProductToCart(UUID customerId,
                                 UUID productId,
                                 int quantity) {
    cartRepo.addProductToCart(customerId, productId, quantity);
    return "Added to cart";
  }

  @Override
  public Cart getCartByCustomer(Customer customer) {
    return cartRepo.findCartByCustomer(customer)
        .orElseThrow(() -> new RuntimeException(MessageConstant.CART_EMPTY));
  }

  @Transactional
  @Override
  public void deleteCart(Cart cart) {
    cartRepo.delete(cart);
  }
}