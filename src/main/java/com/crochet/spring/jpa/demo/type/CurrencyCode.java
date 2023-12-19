package com.crochet.spring.jpa.demo.type;

import lombok.Getter;

@Getter
public enum CurrencyCode {
    USD("USD");

    CurrencyCode(String value) {
        this.value = value;
    }

    private final String value;
}
