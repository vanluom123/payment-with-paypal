package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.payload.dto.paypal.OrderResponseDTO;

import java.util.UUID;

public interface OrderPatternDetailService {
    OrderResponseDTO createPayment(UUID customerId, UUID patternId);

    String processPayPalOrderDetail(String transactionId);
}
