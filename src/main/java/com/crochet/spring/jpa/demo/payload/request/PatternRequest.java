package com.crochet.spring.jpa.demo.payload.request;

import com.crochet.spring.jpa.demo.type.CurrencyCode;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class PatternRequest {
    private String name;
    private double price;
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;
}
