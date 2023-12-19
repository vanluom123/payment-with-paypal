package com.crochet.spring.jpa.demo.payload.dto;

import lombok.Data;

@Data
public class LinkDTO {
    private String href;
    private String rel;
    private String method;
}
