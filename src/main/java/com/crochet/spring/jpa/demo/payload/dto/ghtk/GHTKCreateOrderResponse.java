package com.crochet.spring.jpa.demo.payload.dto.ghtk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GHTKCreateOrderResponse {
    @SerializedName("success")
    @Expose
    public boolean success;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("order")
    @Expose
    @Valid
    public GHTKOrder order;
}
