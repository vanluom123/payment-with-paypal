package com.crochet.spring.jpa.demo.payload.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
public class OrderResult {
    private String orderDate;
    private Integer quantity;
    private Double totalPrice;
}
