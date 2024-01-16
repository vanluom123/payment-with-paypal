package com.crochet.spring.jpa.demo.dto.ghn.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GHNProvince {
    @SerializedName("ProvinceID")
    @Expose
    public long provinceID;
    @SerializedName("ProvinceName")
    @Expose
    public String provinceName;
    @SerializedName("Code")
    @Expose
    public String code;
}
