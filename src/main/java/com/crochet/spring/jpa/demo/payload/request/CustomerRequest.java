package com.crochet.spring.jpa.demo.payload.request;

import lombok.Data;

@Data
public class CustomerRequest {
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String username;
    private String password;
}
