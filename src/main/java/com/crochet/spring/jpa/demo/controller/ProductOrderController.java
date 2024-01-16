package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.dto.CheckoutProductOrderDTO;
import com.crochet.spring.jpa.demo.service.ProductOrderService;
import com.crochet.spring.jpa.demo.type.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/order/products")
public class ProductOrderController {
  @Autowired
  private ProductOrderService productOrderService;

  @GetMapping("/checkout")
  public ResponseEntity<CheckoutProductOrderDTO> checkout(
      @RequestParam("customerId") UUID customerId
  ) {
    var response = productOrderService.checkOutOrder(customerId);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/processOrder")
  public ResponseEntity<String> processOrder(
      @RequestParam("payment_method") PaymentMethod paymentMethod,
      @RequestBody CheckoutProductOrderDTO response
  ) {
    response.setPaymentMethod(paymentMethod);
    var result = productOrderService.processOrder(response);
    return ResponseEntity.ok(result);
  }
}
