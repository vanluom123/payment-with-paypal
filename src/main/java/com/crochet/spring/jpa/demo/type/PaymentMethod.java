package com.crochet.spring.jpa.demo.type;

import lombok.Getter;

@Getter
public enum PaymentMethod {
  CASH_ON_DELIVERY("Cash on delivery"),
  PAYPAL("PayPal");

  PaymentMethod(String value) {
    this.value = value;
  }

  private final String value;
}
