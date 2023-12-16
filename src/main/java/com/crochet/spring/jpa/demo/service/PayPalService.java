package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.payload.request.PayPalOrderRequest;
import org.springframework.stereotype.Service;
import org.xiaofeng.webclient.service.WebClientService;
import org.xiaofeng.webclient.type.HttpMethod;

@Service
public class PayPalService {
    private final String userName = "AYw-XWRSOe3r11W37cF26rxf3tUBn8X1dPala2Lux2cGHbK6NYMiyYB_2SES46YjIx1a9zLuMSHSJpYD";
    private final String password = "EENLA_G2RtWbTYgY6i90ApZkYdctBuY2AcgnsHhct0sbCnwWiM_J55NTmmEsLbiDcNpnLEfBWPneTmwF";

    private final WebClientService webClientService;

    public PayPalService(WebClientService webClientService) {
        webClientService.builder()
                .defaultHeaders(header -> header.setBasicAuth(userName, password));
        this.webClientService = webClientService;
    }

    public String createProduct(PayPalOrderRequest request) {
        String uri = "https://api-m.sandbox.paypal.com/v2/checkout/orders";
        String requestJson = "{\"intent\":\"CAPTURE\",\"purchase_units\":[{\"amount\":{\"currency_code\":\"USD\",\"value\":\"100.00\"}}]}";
        String response = webClientService.invokeApi(uri,
                HttpMethod.POST,
                request,
                String.class,
                h -> {
                    h.set("Content-Type", "application/json");
                    h.set("Accept", "application/json");
                }).block();
        return response;
    }
}
