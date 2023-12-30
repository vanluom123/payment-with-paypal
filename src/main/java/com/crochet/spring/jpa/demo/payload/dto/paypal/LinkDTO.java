package com.crochet.spring.jpa.demo.payload.dto.paypal;

import lombok.Data;

@Data
public class LinkDTO {
    private String href;
    private String rel;
    private String method;
}
