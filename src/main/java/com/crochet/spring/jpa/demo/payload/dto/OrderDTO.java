package com.crochet.spring.jpa.demo.payload.dto;

import com.crochet.spring.jpa.demo.type.OrderIntent;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDTO {
    @Enumerated(EnumType.STRING)
    private OrderIntent intent;
    @SerializedName("purchase_units")
    private List<PurchaseUnit> purchaseUnits;
    @SerializedName("application_context")
    private PayPalAppContextDTO applicationContext;
}
