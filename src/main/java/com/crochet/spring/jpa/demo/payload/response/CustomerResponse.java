package com.crochet.spring.jpa.demo.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String username;
    private String password;
}
