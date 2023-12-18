package com.crochet.spring.jpa.demo.service.contact;

import com.crochet.spring.jpa.demo.payload.request.ConfirmationOrderRequest;
import com.crochet.spring.jpa.demo.payload.request.PayPalOrderRequest;
import com.crochet.spring.jpa.demo.payload.response.AccessTokenResponse;
import com.crochet.spring.jpa.demo.payload.response.CapturePaymentResponse;
import com.crochet.spring.jpa.demo.payload.response.ConfirmOrderResponse;
import com.crochet.spring.jpa.demo.payload.response.PayPalOrderDetailResponse;
import com.crochet.spring.jpa.demo.payload.response.PaymentResponse;

public interface PayPalService {
    AccessTokenResponse getAccessToken();

    PaymentResponse createOrder(PayPalOrderRequest request);

    PayPalOrderDetailResponse getOrderDetail(String orderId);

    ConfirmOrderResponse confirmOrder(String orderId, ConfirmationOrderRequest request);

    CapturePaymentResponse capturePaymentOrder(String orderId);
}
