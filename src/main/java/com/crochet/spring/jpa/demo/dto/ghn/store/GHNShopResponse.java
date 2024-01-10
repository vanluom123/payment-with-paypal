package com.crochet.spring.jpa.demo.dto.ghn.store;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GHNShopResponse {
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
