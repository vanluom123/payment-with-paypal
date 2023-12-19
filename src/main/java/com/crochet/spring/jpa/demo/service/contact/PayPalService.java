package com.crochet.spring.jpa.demo.service.contact;

import com.crochet.spring.jpa.demo.payload.request.paypal.ConfirmationOrderRequest;
import com.crochet.spring.jpa.demo.payload.request.paypal.PayPalOrderRequest;
import com.crochet.spring.jpa.demo.payload.response.paypal.AccessTokenResponse;
import com.crochet.spring.jpa.demo.payload.response.paypal.CapturePaymentResponse;
import com.crochet.spring.jpa.demo.payload.response.paypal.ConfirmOrderResponse;
import com.crochet.spring.jpa.demo.payload.response.paypal.PayPalOrderDetailResponse;
import com.crochet.spring.jpa.demo.payload.response.paypal.PaymentResponse;

public interface PayPalService {
    AccessTokenResponse getAccessToken();

    PaymentResponse createOrder(PayPalOrderRequest request);

    PayPalOrderDetailResponse getOrderDetail(String orderId);

    ConfirmOrderResponse comfirm(String orderId, ConfirmationOrderRequest request);

    CapturePaymentResponse capturePayment(String orderId);
}
