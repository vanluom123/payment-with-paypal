package com.crochet.spring.jpa.demo.dto.ghn.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class GHNData {
    @SerializedName("district_encode")
    @Expose
    public String districtEncode;
    @SerializedName("expected_delivery_time")
    @Expose
    public String expectedDeliveryTime;
    @SerializedName("fee")
    @Expose
    @Valid
    public GHNFee fee;
    @SerializedName("order_code")
    @Expose
    public String orderCode;
    @SerializedName("sort_code")
    @Expose
    public String sortCode;
    @SerializedName("total_fee")
    @Expose
    public String totalFee;
    @SerializedName("trans_type")
    @Expose
    public String transType;
    @SerializedName("ward_encode")
    @Expose
    public String wardEncode;
}
