package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.payload.request.paypal.PayPalOrderRequest;
import com.crochet.spring.jpa.demo.service.contact.OrderPatternService;
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
@RequestMapping("/api/checkout")
public class CheckoutController {
    @Autowired
    private OrderPatternService orderPatternService;

    @PostMapping("/create-payment")
    public ResponseEntity<String> createPayment(@RequestParam("customerId") String customerId,
                                                @RequestParam("patternId") String patternId,
                                                @RequestBody PayPalOrderRequest request) {
        var response = orderPatternService.createPayment(customerId, patternId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/success")
    public ResponseEntity<String> paymentSuccess(HttpServletRequest request) {
        var transactionId = request.getParameter("token");
        var response = orderPatternService.processPayPalOrderDetail(transactionId);
        return ResponseEntity.ok(response);
    }
}
