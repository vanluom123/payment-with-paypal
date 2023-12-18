package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.payload.request.paypal.PayPalOrderRequest;
import com.crochet.spring.jpa.demo.service.contact.OrderPatternDetailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order-pattern-detail")
public class OrderPatternDetailController {
    @Autowired
    private OrderPatternDetailService orderPatternDetailService;

    @PostMapping("/create")
    public ResponseEntity<String> createPayment(@RequestParam("customerId") String customerId,
                                                @RequestParam("patternId") String patternId,
                                                @RequestBody PayPalOrderRequest request) {
        var response = orderPatternDetailService.createPayment(customerId, patternId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/success")
    public ResponseEntity<String> paymentSuccess(HttpServletRequest request) {
        var transactionId = request.getParameter("token");
        var response = orderPatternDetailService.processPayPalOrderDetail(transactionId);
        return ResponseEntity.ok(response);
    }
}
