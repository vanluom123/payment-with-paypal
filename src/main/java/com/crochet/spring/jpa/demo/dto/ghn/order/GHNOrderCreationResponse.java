package com.crochet.spring.jpa.demo.dto.ghn.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class GHNOrderCreationResponse {
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
