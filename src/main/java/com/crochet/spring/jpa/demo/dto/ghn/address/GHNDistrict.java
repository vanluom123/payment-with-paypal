package com.crochet.spring.jpa.demo.dto.ghn.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GHNDistrict {
    @SerializedName("DistrictID")
    @Expose
    public long districtID;
    @SerializedName("ProvinceID")
    @Expose
    public long provinceID;
    @SerializedName("DistrictName")
    @Expose
    public String districtName;
    @SerializedName("Code")
    @Expose
    public String code;
    @SerializedName("Type")
    @Expose
    public long type;
    @SerializedName("SupportType")
    @Expose
    public long supportType;
}
