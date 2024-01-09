package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.payload.dto.CheckoutOrderProductDTO;

import java.util.UUID;

public interface OrderProductService {
    CheckoutOrderProductDTO checkOutOrder(UUID customerId);

    //    @Transactional
    String processOrder(CheckoutOrderProductDTO checkoutOrder);
}
