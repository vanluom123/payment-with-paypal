package com.crochet.spring.jpa.demo.type;

import lombok.Getter;

@Getter
public enum RoleType {
    USER("USER"),
    ADMIN("ADMIN");

    RoleType(String value) {
        this.value = value;
    }

    private final String value;
}
