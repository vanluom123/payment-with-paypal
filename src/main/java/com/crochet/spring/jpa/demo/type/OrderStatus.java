package com.crochet.spring.jpa.demo.type;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("PENDING"),
    DELIVERED("DELIVERED"),
    SHIPPED("SHIPPED");

    OrderStatus(String value) {
        this.value = value;
    }

    private final String value;
}
