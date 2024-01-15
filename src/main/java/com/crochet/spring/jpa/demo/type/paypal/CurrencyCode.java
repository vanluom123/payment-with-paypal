package com.crochet.spring.jpa.demo.type.paypal;

import lombok.Getter;

@Getter
public enum CurrencyCode {
  USD("USD"),
  VND("VND");

  CurrencyCode(String value) {
    this.value = value;
  }

  private final String value;
}
