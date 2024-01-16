package com.crochet.spring.jpa.demo.dto.ghn.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
public class GHNDistrictDTO {
    @SerializedName("code")
    @Expose
    public long code;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    @Valid
    public List<GHNDistrict> data;
}
