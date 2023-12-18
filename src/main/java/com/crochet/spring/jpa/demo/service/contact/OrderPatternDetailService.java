package com.crochet.spring.jpa.demo.service.contact;

import com.crochet.spring.jpa.demo.payload.request.paypal.PayPalOrderRequest;

public interface OrderPatternDetailService {
    String createPayment(String orderId, String patternId, PayPalOrderRequest request);

    String processPayPalOrderDetail(String transactionId);
}
