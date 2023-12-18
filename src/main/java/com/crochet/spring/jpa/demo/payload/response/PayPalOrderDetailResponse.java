package com.crochet.spring.jpa.demo.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayPalOrderDetailResponse {
    private String id;
    private String intent;
    private String status;

    @JsonProperty("purchase_units")
    private List<PurchaseUnit> purchaseUnits;

    @JsonProperty("create_time")
    private String createTime;

    private List<Link> links;

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PurchaseUnit {
        @JsonProperty("reference_id")
        private String referenceId;
        private Amount amount;
        private Payee payee;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Amount {
        @JsonProperty("currency_code")
        private String currencyCode;
        private String value;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payee {
        @JsonProperty("email_address")
        private String emailAddress;
        @JsonProperty("merchant_id")
        private String merchantId;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Link {
        private String href;
        private String rel;
        private String method;
    }
}

