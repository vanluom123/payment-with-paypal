package com.crochet.spring.jpa.demo.payload.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProductRequest {
    private UUID id;
    private UUID categoryId;
    private String name;
    private double price;
    private String description;
    private int height;
    private int width;
    private int length;
    private int weight;
}
