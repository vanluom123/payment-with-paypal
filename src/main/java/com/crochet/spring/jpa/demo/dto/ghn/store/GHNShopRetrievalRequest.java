package com.crochet.spring.jpa.demo.dto.ghn.store;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GHNShopRetrievalRequest {
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
