package com.crochet.spring.jpa.demo.dto.paypal;

import com.crochet.spring.jpa.demo.dto.OrderDTOBuilder;
import com.crochet.spring.jpa.demo.type.paypal.OrderIntent;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {
    @Enumerated(EnumType.STRING)
    private OrderIntent intent;
    @SerializedName("purchase_units")
    private List<PurchaseUnit> purchaseUnits;
    @SerializedName("application_context")
    private PayPalAppContextDTO applicationContext;

    public static OrderDTOBuilder builder() {
        return new OrderDTOBuilder();
    }
}
