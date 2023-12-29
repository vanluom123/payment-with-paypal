package com.crochet.spring.jpa.demo.payload.dto.ghtk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.Valid;

import java.util.List;

public class GHTKOrderResponse {
    @SerializedName("partner_id")
    @Expose
    public String partnerId;
    @SerializedName("label")
    @Expose
    public String label;
    @SerializedName("area")
    @Expose
    public String area;
    @SerializedName("fee")
    @Expose
    public String fee;
    @SerializedName("insurance_fee")
    @Expose
    public String insuranceFee;
    @SerializedName("estimated_pick_time")
    @Expose
    public String estimatedPickTime;
    @SerializedName("estimated_deliver_time")
    @Expose
    public String estimatedDeliverTime;
    @SerializedName("products")
    @Expose
    @Valid
    public List<GHTKProduct> products;
    @SerializedName("status_id")
    @Expose
    public long statusId;
}
