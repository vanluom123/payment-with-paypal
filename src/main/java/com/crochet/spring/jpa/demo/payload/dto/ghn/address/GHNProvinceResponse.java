package com.crochet.spring.jpa.demo.payload.dto.ghn.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
public class GHNProvinceResponse {
    @SerializedName("code")
    @Expose
    public long code;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    @Valid
    public List<GHNProvince> data;
}
