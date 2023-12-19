package com.crochet.spring.jpa.demo.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderProductDetailResponse {
    private String orderDate;
    private Integer quantity;
    private Double totalPrice;
}
