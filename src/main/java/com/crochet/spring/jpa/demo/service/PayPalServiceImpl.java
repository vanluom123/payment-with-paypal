package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.payload.request.ConfirmationOrderRequest;
import com.crochet.spring.jpa.demo.payload.request.PayPalOrderRequest;
import com.crochet.spring.jpa.demo.payload.response.AccessTokenResponse;
import com.crochet.spring.jpa.demo.payload.response.CapturePaymentResponse;
import com.crochet.spring.jpa.demo.payload.response.ConfirmOrderResponse;
import com.crochet.spring.jpa.demo.payload.response.PayPalOrderDetailResponse;
import com.crochet.spring.jpa.demo.payload.response.PaymentResponse;
import com.crochet.spring.jpa.demo.service.contact.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.xiaofeng.webclient.service.WebClientService;
import org.xiaofeng.webclient.type.HttpMethod;

@Service
public class PayPalServiceImpl implements PayPalService {
    private final String userName = "AYw-XWRSOe3r11W37cF26rxf3tUBn8X1dPala2Lux2cGHbK6NYMiyYB_2SES46YjIx1a9zLuMSHSJpYD";
    private final String password = "EENLA_G2RtWbTYgY6i90ApZkYdctBuY2AcgnsHhct0sbCnwWiM_J55NTmmEsLbiDcNpnLEfBWPneTmwF";

    private final WebClientService webClientService;

    @Autowired
    public PayPalServiceImpl(WebClientService webClientService) {
        webClientService.builder()
                .defaultHeaders(header -> {
                    header.setBasicAuth(userName, password);
                    header.setContentType(MediaType.APPLICATION_JSON);
                });
        this.webClientService = webClientService;
    }

    @Override
    public AccessTokenResponse getAccessToken() {
        String uri = "https://api-m.sandbox.paypal.com/v1/oauth2/token";
        var bodyInserters = BodyInserters.fromFormData("grant_type", "client_credentials");
        var accessTokenResponse = webClientService.invokeApi(uri,
                HttpMethod.POST,
                bodyInserters,
                AccessTokenResponse.class,
                header -> {
                    header.setBasicAuth(userName, password);
                    header.set("Content-Type", "application/x-www-form-urlencoded");
                }).block();
        webClientService.builder()
                .defaultHeaders(header -> header.setBearerAuth(accessTokenResponse.getAccessToken()));
        return accessTokenResponse;
    }

    @Override
    public PaymentResponse createOrder(PayPalOrderRequest request) {
        String uri = "https://api-m.sandbox.paypal.com/v2/checkout/orders";
        PaymentResponse paymentResponse = webClientService.invokeApi(uri,
                HttpMethod.POST,
                request,
                PaymentResponse.class).block();
        return paymentResponse;
    }

    @Override
    public PayPalOrderDetailResponse getOrderDetail(String orderId) {
        String uri = "https://api-m.sandbox.paypal.com/v2/checkout/orders/" + orderId;
        PayPalOrderDetailResponse response = webClientService.invokeApi(uri,
                HttpMethod.GET,
                PayPalOrderDetailResponse.class).block();
        return response;
    }

    @Override
    public ConfirmOrderResponse confirmOrder(String orderId, ConfirmationOrderRequest request) {
        String uri = "https://api-m.sandbox.paypal.com/v2/checkout/orders/" + orderId + "/confirm-payment-source";
        var response = webClientService.invokeApi(uri,
                HttpMethod.POST,
                request,
                ConfirmOrderResponse.class).block();
        return response;
    }

    @Override
    public CapturePaymentResponse capturePaymentOrder(String orderId) {
        String uri = "https://api-m.sandbox.paypal.com/v2/checkout/orders/" + orderId + "/capture";
        var response = webClientService.invokeApi(uri,
                HttpMethod.POST,
                CapturePaymentResponse.class).block();
        return response;
    }
}
