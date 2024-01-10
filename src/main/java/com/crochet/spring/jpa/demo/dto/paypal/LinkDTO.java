package com.crochet.spring.jpa.demo.dto.paypal;

import lombok.Data;

@Data
public class LinkDTO {
    private String href;
    private String rel;
    private String method;
}
