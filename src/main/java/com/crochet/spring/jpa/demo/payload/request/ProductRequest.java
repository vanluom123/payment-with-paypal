package com.crochet.spring.jpa.demo.payload.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
    private String id;
    private String name;
    private double price;
    private String description;
}
