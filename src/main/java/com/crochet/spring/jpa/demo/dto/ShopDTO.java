package com.crochet.spring.jpa.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShopDTO {
    private String shopName;
    private String phone;
    private String address;
    private String wardCode;
    private String wardName;
    private int districtID;
    private String districtName;
    private String provinceName;
}
