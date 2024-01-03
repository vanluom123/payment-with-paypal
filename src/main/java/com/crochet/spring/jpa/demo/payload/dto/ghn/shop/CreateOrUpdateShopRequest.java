package com.crochet.spring.jpa.demo.payload.dto.ghn.shop;

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
    private String wardCode;
    private String wardName;
    private int districtId;
    private String districtName;
    private String provinceName;
}