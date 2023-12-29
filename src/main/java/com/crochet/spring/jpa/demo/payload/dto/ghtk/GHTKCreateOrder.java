package com.crochet.spring.jpa.demo.payload.dto.ghtk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GHTKCreateOrder {
    @SerializedName("products")
    @Expose
    @Valid
    public List<GHTKProduct> products;
    @SerializedName("order")
    @Expose
    @Valid
    public GHTKOrder order;
}