package com.crochet.spring.jpa.demo.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateOrUpdateCategoryRequest {
    private UUID id;
    private String name;
}
