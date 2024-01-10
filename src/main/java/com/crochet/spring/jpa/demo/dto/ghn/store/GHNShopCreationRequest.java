package com.crochet.spring.jpa.demo.dto.ghn.store;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GHNShopCreationRequest {
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