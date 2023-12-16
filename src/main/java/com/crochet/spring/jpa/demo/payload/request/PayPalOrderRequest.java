package com.crochet.spring.jpa.demo.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Builder
@AllArgsConstructor
public class PayPalOrderRequest {
    private String intent;
    private List<PurchaseUnit> purchase_units;

    @Getter @Setter
    @Builder
    @AllArgsConstructor
    public static class PurchaseUnit {
        private PayPalAmount amount;

        @Getter @Setter
        @Builder
        @AllArgsConstructor
        public static class PayPalAmount {
            private String currency_code;
            private String value;
        }
    }
}
