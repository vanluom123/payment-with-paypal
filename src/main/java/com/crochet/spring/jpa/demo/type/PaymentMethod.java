package com.crochet.spring.jpa.demo.type;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    PAYMENT_AFTER_RECEIVED(0),
    PAYMENT_PAYPAL(1);

    PaymentMethod(int value) {
        this.value = value;
    }

    private final int value;
}
