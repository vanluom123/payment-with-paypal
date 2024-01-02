package com.crochet.spring.jpa.demo.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateOrUpdateShopRequest {
    private UUID id;
    private UUID customerId;
    private String shopName;
    private String phone;
    private String address;
    private String wardName;
    private String districtName;
    private String provinceName;
}