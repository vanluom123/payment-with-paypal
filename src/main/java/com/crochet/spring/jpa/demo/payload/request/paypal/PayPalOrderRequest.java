package com.crochet.spring.jpa.demo.payload.request.paypal;

import com.crochet.spring.jpa.demo.type.OrderIntent;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PayPalOrderRequest {
    @Enumerated(EnumType.STRING)
    private OrderIntent intent;
    @JsonProperty("purchase_units")
    private List<PurchaseUnit> purchaseUnits;

    @Data
    @NoArgsConstructor
    public static class PurchaseUnit {
        private PayPalAmount amount;

        @Data
        @NoArgsConstructor
        public static class PayPalAmount {
            @JsonProperty("currency_code")
            private String currencyCode;
            private String value;
        }
    }
}
