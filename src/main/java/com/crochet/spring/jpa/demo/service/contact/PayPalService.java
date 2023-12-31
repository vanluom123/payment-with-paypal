package com.crochet.spring.jpa.demo.service.contact;

import com.crochet.spring.jpa.demo.payload.dto.OrderDTO;
import com.crochet.spring.jpa.demo.payload.response.AccessTokenResponse;

public interface PayPalService {
    AccessTokenResponse getAccessToken();

    String createOrder(OrderDTO orderDTO);

    String getOrderDetail(String orderId);

    String confirm(String orderId, String request);

    String capturePayment(String orderId);
}
