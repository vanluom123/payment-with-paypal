package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.payload.request.ConfirmationOrderRequest;
import com.crochet.spring.jpa.demo.payload.request.PayPalOrderRequest;
import com.crochet.spring.jpa.demo.payload.response.AccessTokenResponse;
import com.crochet.spring.jpa.demo.payload.response.CapturePaymentResponse;
import com.crochet.spring.jpa.demo.payload.response.ConfirmOrderResponse;
import com.crochet.spring.jpa.demo.payload.response.PayPalOrderDetailResponse;
import com.crochet.spring.jpa.demo.payload.response.PaymentResponse;
import com.crochet.spring.jpa.demo.service.contact.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/paypal")
public class PayPalController {
    private final PayPalService payPalService;

    @Autowired
    public PayPalController(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @PostMapping("/access-token")
    public Mono<AccessTokenResponse> getAccessToken() {
        return Mono.just(payPalService.getAccessToken());
    }

    @PostMapping("/create-order")
    public Mono<PaymentResponse> createOrder(@RequestBody PayPalOrderRequest request) {
        return Mono.just(payPalService.createOrder(request));
    }

    @GetMapping("/order-detail")
    public Mono<PayPalOrderDetailResponse> getOrderDetail(@RequestParam String orderId) {
        return Mono.just(payPalService.getOrderDetail(orderId));
    }

    @PostMapping("/confirm-order")
    public Mono<ConfirmOrderResponse> confirmOrder(@RequestParam String orderId, @RequestBody ConfirmationOrderRequest request) {
        return Mono.just(payPalService.confirmOrder(orderId, request));
    }

    @PostMapping("/capture-payment")
    public Mono<CapturePaymentResponse> capturePaymentOrder(@RequestParam String orderId) {
        return Mono.just(payPalService.capturePaymentOrder(orderId));
    }
}
