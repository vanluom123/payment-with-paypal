package com.crochet.spring.jpa.demo.payload.dto.ghn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GetShopsRequestDTO {
    @SerializedName("offset")
    @Expose
    public long offset;
    @SerializedName("limit")
    @Expose
    public long limit;
    @SerializedName("client_phone")
    @Expose
    public String clientPhone;
}
