package com.crochet.spring.jpa.demo.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateContactRequest {
    private UUID customerId;
    private String address;
    private String phone;
    private int wardCode;
    private String wardName;
    private int districtID;
    private String districtName;
    private String provinceName;
}
