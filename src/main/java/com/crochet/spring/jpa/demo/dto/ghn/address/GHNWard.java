package com.crochet.spring.jpa.demo.dto.ghn.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GHNWard {
    @SerializedName("WardCode")
    @Expose
    public long wardCode;
    @SerializedName("DistrictID")
    @Expose
    public int districtID;
    @SerializedName("WardName")
    @Expose
    public String wardName;
}
