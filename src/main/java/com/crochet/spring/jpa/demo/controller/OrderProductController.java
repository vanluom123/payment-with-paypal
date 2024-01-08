package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.payload.response.CheckoutOrderProductResponse;
import com.crochet.spring.jpa.demo.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/order/products")
public class OrderProductController {
    @Autowired
    private OrderProductService orderProductService;

    @GetMapping("/checkout")
    public ResponseEntity<CheckoutOrderProductResponse> checkout(
            @RequestParam("customerId") UUID customerId
    ) {
        var response = orderProductService.checkOutOrder(customerId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/processOrder")
    public ResponseEntity<String> processOrder(@RequestBody CheckoutOrderProductResponse response) {
        var result = orderProductService.processOrder(response);
        return ResponseEntity.ok(result);
    }
}
