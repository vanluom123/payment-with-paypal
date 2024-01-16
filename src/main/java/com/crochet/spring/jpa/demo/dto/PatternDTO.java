package com.crochet.spring.jpa.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class PatternDTO {
    private String id;
    private String name;
    private double price;
    private List<String> files;
}
