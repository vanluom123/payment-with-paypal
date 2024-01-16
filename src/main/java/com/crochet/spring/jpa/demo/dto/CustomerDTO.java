package com.crochet.spring.jpa.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {
    private String id;
    private String name;
    private String email;
    private String username;
    private String password;
}
