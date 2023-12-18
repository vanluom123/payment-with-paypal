package com.crochet.spring.jpa.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
public class OrderProductDetailResponse {
    private String orderDate;
    private Integer quantity;
    private Double totalPrice;
}
