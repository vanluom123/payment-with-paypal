package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.payload.request.OrderRequest;
import com.crochet.spring.jpa.demo.payload.response.OrderResponse;
import com.crochet.spring.jpa.demo.service.contact.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order-product")
    public ResponseEntity<OrderResponse> orderProduct(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.orderProduct(request));
    }
}
