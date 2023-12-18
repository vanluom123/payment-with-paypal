package com.crochet.spring.jpa.demo.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private String id;
    private String name;
    private double price;
    private String description;
    private List<FileResponse> files;
}
