package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.dto.paypal.OrderResponseDTO;

import java.util.UUID;

public interface PatternOrderDetailService {
  OrderResponseDTO createPayment(UUID customerId, UUID patternId);

  String processPayPalOrderDetail(String transactionId);
}
