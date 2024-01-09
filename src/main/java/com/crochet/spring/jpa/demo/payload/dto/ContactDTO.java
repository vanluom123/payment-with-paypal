package com.crochet.spring.jpa.demo.payload.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDTO {
    private String name;
    private String address;
    private String phone;
    private String wardCode;
    private String wardName;
    private int districtID;
    private String districtName;
    private String provinceName;
    public boolean isDefault;
}
