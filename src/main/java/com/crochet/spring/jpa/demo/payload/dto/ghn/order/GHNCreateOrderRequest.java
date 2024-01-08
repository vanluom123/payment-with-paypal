package com.crochet.spring.jpa.demo.payload.dto.ghn.order;

import com.crochet.spring.jpa.demo.common.AppConstant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GHNCreateOrderRequest {
    @SerializedName("payment_type_id")
    @Expose
    @Builder.Default
    private Integer paymentTypeId = AppConstant.BUYER_PAYMENT;

    @SerializedName("note")
    @Expose
    @Builder.Default
    private String note = "";

    @SerializedName("required_note")
    @Expose
    @Builder.Default
    private String requiredNote = "CHOTHUHANG";

    @SerializedName("from_name")
    @Expose
    private String fromName;

    @SerializedName("from_phone")
    @Expose
    private String fromPhone;

    @SerializedName("from_address")
    @Expose
    private String fromAddress;

    @SerializedName("from_ward_name")
    @Expose
    private String fromWardName;

    @SerializedName("from_district_name")
    @Expose
    private String fromDistrictName;

    @SerializedName("from_province_name")
    @Expose
    private String fromProvinceName;

    @SerializedName("return_phone")
    @Expose
    private String returnPhone;

    @SerializedName("return_address")
    @Expose
    private String returnAddress;

    @SerializedName("return_district_id")
    @Expose
    private Object returnDistrictId;

    @SerializedName("return_ward_code")
    @Expose
    private String returnWardCode;

    @SerializedName("client_order_code")
    @Expose
    private String clientOrderCode;

    @SerializedName("to_name")
    @Expose
    private String toName;

    @SerializedName("to_phone")
    @Expose
    private String toPhone;

    @SerializedName("to_address")
    @Expose
    private String toAddress;

    @SerializedName("to_ward_code")
    @Expose
    private String toWardCode;

    @SerializedName("to_district_id")
    @Expose
    private Integer toDistrictId;

    @SerializedName("cod_amount")
    @Expose
    @Builder.Default
    private Integer codAmount = 0;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("weight")
    @Expose
    private Integer weight;

    @SerializedName("length")
    @Expose
    private Integer length;

    @SerializedName("width")
    @Expose
    private Integer width;

    @SerializedName("height")
    @Expose
    private Integer height;

    @SerializedName("pick_station_id")
    @Expose
    private Integer pickStationId;

    @SerializedName("deliver_station_id")
    @Expose
    private Integer deliverStationId;

    @SerializedName("insurance_value")
    @Expose
    private Integer insuranceValue;

    @SerializedName("service_id")
    @Expose
    @Builder.Default
    private Integer serviceId = 0;

    @SerializedName("service_type_id")
    @Expose
    @Builder.Default
    private Integer serviceTypeId = AppConstant.ECOMMERCE_DELIVERY;

    @SerializedName("coupon")
    @Expose
    private String coupon;

    @SerializedName("pick_shift")
    @Expose
    private List<Integer> pickShift;

    @SerializedName("items")
    @Expose
    private List<GHNItem> items;
}
