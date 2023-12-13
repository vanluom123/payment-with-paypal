package com.crochet.spring.jpa.demo.payload.result;

import lombok.Data;

import java.util.List;

@Data
public class ProductResult {
    private String id;
    private String name;
    private double price;
    private String description;
    private List<FileResult> files;
}
