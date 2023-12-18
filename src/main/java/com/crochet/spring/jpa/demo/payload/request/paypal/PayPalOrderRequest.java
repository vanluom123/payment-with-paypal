package com.crochet.spring.jpa.demo.payload.request.paypal;

import com.crochet.spring.jpa.demo.type.OrderIntent;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class PayPalOrderRequest {
    @Enumerated(EnumType.STRING)
    private OrderIntent intent;
    @JsonProperty("purchase_units")
    private List<PurchaseUnit> purchaseUnits;

    @Getter @Setter
    @NoArgsConstructor
    public static class PurchaseUnit {
        private PayPalAmount amount;

        @Getter @Setter
        @NoArgsConstructor
        public static class PayPalAmount {
            @JsonProperty("currency_code")
            private String currencyCode;
            private String value;
        }
    }
}
