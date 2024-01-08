package com.crochet.spring.jpa.demo.payload.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProductResponse {
    private UUID id;
    private String name;
    private double price;
    private String description;
    private int height;
    private int width;
    private int length;
    private int weight;
    private List<String> files;
    private CategoryResponse categoryResponse;
}
