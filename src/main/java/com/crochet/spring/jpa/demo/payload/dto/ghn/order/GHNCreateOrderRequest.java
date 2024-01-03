package com.crochet.spring.jpa.demo.payload.dto.ghn.order;

import com.crochet.spring.jpa.demo.common.AppConstant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GHNCreateOrderRequest {
    @SerializedName("payment_type_id")
    @Expose
    public int paymentTypeId = AppConstant.BUYER_PAYMENT;

    @SerializedName("note")
    @Expose
    public String note;

    @SerializedName("required_note")
    @Expose
    @Enumerated(EnumType.STRING)
    public String requiredNote = "CHOTHUHANG";

    @SerializedName("from_name")
    @Expose
    public String fromName = "Little crochet Tieu Phuong";

    @SerializedName("from_phone")
    @Expose
    public String fromPhone = "0969545937";

    @SerializedName("from_address")
    @Expose
    public String fromAddress = "trường Khoa học Xã hội và Nhân văn làng Đại học";

    @SerializedName("from_ward_name")
    @Expose
    public String fromWardName = "Phường Đông Hòa";

    @SerializedName("from_district_name")
    @Expose
    public String fromDistrictName = "Thành phố Dĩ An";

    @SerializedName("from_province_name")
    @Expose
    public String fromProvinceName = "Bình Dương";

    @SerializedName("to_name")
    @Expose
    public String toName;

    @SerializedName("to_phone")
    @Expose
    public String toPhone;

    @SerializedName("to_address")
    @Expose
    public String toAddress;

    @SerializedName("to_ward_code")
    @Expose
    public String toWardCode;

    @SerializedName("to_district_id")
    @Expose
    public int toDistrictId;

    @SerializedName("content")
    @Expose
    public String content;

    @SerializedName("weight")
    @Expose
    public int weight;

    @SerializedName("length")
    @Expose
    public int length;

    @SerializedName("width")
    @Expose
    public int width;

    @SerializedName("height")
    @Expose
    public int height;

    @SerializedName("service_id")
    @Expose
    public int serviceId = 0;

    @SerializedName("service_type_id")
    @Expose
    public int serviceTypeId = AppConstant.ECOMMERCE_DELIVERY;

    @SerializedName("items")
    @Expose
    public List<GHNItem> items;
}
