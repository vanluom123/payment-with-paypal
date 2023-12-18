package com.crochet.spring.jpa.demo.service.contact;

import com.crochet.spring.jpa.demo.payload.response.OrderPatternDetailResponse;

public interface OrderPatternDetailService {
    OrderPatternDetailResponse createPayment(String customerId, String patternId);

    String processPayPalOrderDetail(String transactionId);
}
