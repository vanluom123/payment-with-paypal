package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.payload.dto.OrderDTO;
import com.crochet.spring.jpa.demo.payload.response.AccessTokenResponse;
import com.crochet.spring.jpa.demo.service.contact.PayPalService;
import com.google.gson.Gson;
import lombok.SneakyThrows;
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
    private Gson gson;

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
        var json = webClientService.invokeApi(uri,
                HttpMethod.POST,
                bodyInserters,
                header -> {
                    header.setBasicAuth(userName, password);
                    header.set("Content-Type", "application/x-www-form-urlencoded");
                }).block();
        var accessTokenResponse = gson.fromJson(json, AccessTokenResponse.class);
        webClientService.builder()
                .defaultHeaders(header -> header.setBearerAuth(accessTokenResponse.getAccessToken()));
        return accessTokenResponse;
    }

    @SneakyThrows
    @Override
    public String createOrder(OrderDTO orderDTO) {
        String payload = gson.toJson(orderDTO);
        String uri = "https://api-m.sandbox.paypal.com/v2/checkout/orders";
        String paymentResponse = webClientService.invokeApi(uri,
                HttpMethod.POST,
                payload).block();
        return paymentResponse;
    }

    @Override
    public String getOrderDetail(String orderId) {
        String uri = "https://api-m.sandbox.paypal.com/v2/checkout/orders/" + orderId;
        var response = webClientService.invokeApi(uri,
                HttpMethod.GET).block();
        return response;
    }

    @Override
    public String confirm(String orderId, String request) {
        String uri = "https://api-m.sandbox.paypal.com/v2/checkout/orders/" + orderId + "/confirm-payment-source";
        var response = webClientService.invokeApi(uri,
                HttpMethod.POST,
                request).block();
        return response;
    }

    @Override
    public String capturePayment(String orderId) {
        String uri = "https://api-m.sandbox.paypal.com/v2/checkout/orders/" + orderId + "/capture";
        var response = webClientService.invokeApi(uri,
                HttpMethod.POST).block();
        return response;
    }
}
