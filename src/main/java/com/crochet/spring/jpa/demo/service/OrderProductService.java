package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.dto.CheckoutOrderProductDTO;

import java.util.UUID;

public interface OrderProductService {
    CheckoutOrderProductDTO checkOutOrder(UUID customerId, int paymentMethod);

    //    @Transactional
    String processOrder(CheckoutOrderProductDTO checkoutOrder);
}
