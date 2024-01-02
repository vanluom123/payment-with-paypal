package com.crochet.spring.jpa.demo.payload.dto.ghn.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class GHNCreateOrderResponse {
    @SerializedName("code")
    @Expose
    public long code;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    @Valid
    public GHNData data;
}
