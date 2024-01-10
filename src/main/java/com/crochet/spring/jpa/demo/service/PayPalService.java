package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.dto.AccessTokenDTO;
import com.crochet.spring.jpa.demo.dto.paypal.OrderDTO;

public interface PayPalService {
    AccessTokenDTO getAccessToken();

    String createOrder(OrderDTO orderDTO);

    String getOrderDetail(String orderId);

    String confirm(String orderId, String request);

    String capturePayment(String orderId);
}
