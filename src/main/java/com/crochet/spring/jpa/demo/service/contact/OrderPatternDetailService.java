package com.crochet.spring.jpa.demo.service.contact;

import com.crochet.spring.jpa.demo.payload.dto.OrderResponseDTO;

public interface OrderPatternDetailService {
    OrderResponseDTO createPayment(String customerId, String patternId);

    String processPayPalOrderDetail(String transactionId);
}
