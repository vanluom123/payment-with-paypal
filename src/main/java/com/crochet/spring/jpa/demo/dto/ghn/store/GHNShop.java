package com.crochet.spring.jpa.demo.dto.ghn.store;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GHNShop {
    @SerializedName("_id")
    @Expose
    public long id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("ward_code")
    @Expose
    public String wardCode;
    @SerializedName("district_id")
    @Expose
    public long districtId;
    @SerializedName("client_id")
    @Expose
    public long clientId;
    @SerializedName("bank_account_id")
    @Expose
    public long bankAccountId;
}
