package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {
  @Autowired
  private CartService cartService;

  @PostMapping("/add")
  public ResponseEntity<String> addCart(
      @RequestParam("customer_id") UUID customerId,
      @RequestParam("product_id") UUID productId,
      @RequestParam("quantity") int quantity
  ) {
    var result = cartService.addProductToCart(customerId, productId, quantity);
    return ResponseEntity.ok(result);
  }
}
