package com.crochet.spring.jpa.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String username;
    private String password;
}
