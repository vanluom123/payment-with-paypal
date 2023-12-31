package com.crochet.spring.jpa.demo.type;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATED("CREATED"),
    SAVED("SAVED"),
    APPROVED("APPROVED"),
    VOIDED("VOIDED"),
    COMPLETED("COMPLETED"),
    PAYER_ACTION_REQUIRED("PAYER_ACTION_REQUIRED");

    OrderStatus(String value) {
        this.value = value;
    }

    private final String value;
}
