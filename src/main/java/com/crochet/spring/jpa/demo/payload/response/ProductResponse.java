package com.crochet.spring.jpa.demo.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private String id;
    private String name;
    private double price;
    private String description;
    private int height;
    private int width;
    private int length;
    private int weight;
    private List<String> files;
}
