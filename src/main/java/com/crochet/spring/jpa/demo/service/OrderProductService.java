package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.payload.response.CheckoutOrderProductResponse;

import java.util.UUID;

public interface OrderProductService {
    CheckoutOrderProductResponse checkOutOrder(UUID customerId);

    //    @Transactional
    String processOrder(CheckoutOrderProductResponse checkoutOrder);
}
