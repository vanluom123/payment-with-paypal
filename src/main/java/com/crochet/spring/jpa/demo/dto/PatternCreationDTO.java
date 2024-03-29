package com.crochet.spring.jpa.demo.dto;

import com.crochet.spring.jpa.demo.type.paypal.CurrencyCode;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatternCreationDTO {
  private String name;
  private double price;
  @Enumerated(EnumType.STRING)
  private CurrencyCode currencyCode;
}
