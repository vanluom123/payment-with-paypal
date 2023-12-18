package com.crochet.spring.jpa.demo.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class PayPalOrderRequest {
    private String intent;
    private List<PurchaseUnit> purchase_units;

    @Getter @Setter
    @NoArgsConstructor
    public static class PurchaseUnit {
        private PayPalAmount amount;

        @Getter @Setter
        @NoArgsConstructor
        public static class PayPalAmount {
            private String currency_code;
            private String value;
        }
    }
}
