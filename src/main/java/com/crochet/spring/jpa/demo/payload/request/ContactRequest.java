package com.crochet.spring.jpa.demo.payload.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ContactRequest {
    @SerializedName("contact_id")
    private UUID contactId;
    @SerializedName("customer_id")
    private UUID customerId;
    private String name;
    private String address;
    private String phone;
    private String wardCode;
    private String wardName;
    private int districtID;
    private String districtName;
    private String provinceName;
    @SerializedName("is_default")
    private boolean isDefault;
}
