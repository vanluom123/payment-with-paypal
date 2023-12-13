package com.crochet.spring.jpa.demo.payload.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String id;
    private String name;
    private double price;
    private String description;
}
