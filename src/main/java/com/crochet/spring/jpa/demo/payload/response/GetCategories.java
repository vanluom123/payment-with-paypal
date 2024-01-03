package com.crochet.spring.jpa.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GetCategories {
    private UUID id;
    private String name;
}
