package com.crochet.spring.jpa.demo.payload.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProductRequest {
    private UUID id;
    private String name;
    private double price;
    private String description;
}
