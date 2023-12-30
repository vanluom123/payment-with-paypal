package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.service.contact.CartService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private Gson gson;

    @PostMapping("/add")
    public ResponseEntity<String> addCart(
            @RequestParam("customer_id") String customerId,
            @RequestParam("product_id") String productId,
            @RequestParam("quantity") int quantity
    ) {
        var result = cartService.addProductToCart(customerId, productId, quantity);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestParam("customer_id") String cusId) {
        var result = cartService.placeOrder_2(cusId);
        return ResponseEntity.ok(result);
    }
}
