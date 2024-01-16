package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.dto.CheckoutProductOrderDTO;

import java.util.UUID;

public interface ProductOrderService {
  CheckoutProductOrderDTO checkOutOrder(UUID customerId);

  String processOrder(CheckoutProductOrderDTO checkoutOrder);
}
