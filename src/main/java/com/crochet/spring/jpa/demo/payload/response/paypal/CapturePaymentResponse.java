package com.crochet.spring.jpa.demo.payload.response.paypal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CapturePaymentResponse {
    private String id;
    private String status;

    @JsonProperty("payment_source")
    private PaymentSource paymentSource;

    @JsonProperty("purchase_units")
    private List<PurchaseUnit> purchaseUnits;

    private Payer payer;

    private List<Link> links;

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PaymentSource {
        private PayPal paypal;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PayPal {
        private Name name;

        @JsonProperty("email_address")
        private String emailAddress;

        @JsonProperty("account_id")
        private String accountId;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PurchaseUnit {
        @JsonProperty("reference_id")
        private String referenceId;

        private Shipping shipping;

        private Payments payments;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Shipping {
        private Address address;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Address {
        @JsonProperty("address_line_1")
        private String addressLine1;

        @JsonProperty("address_line_2")
        private String addressLine2;

        @JsonProperty("admin_area_2")
        private String adminArea2;

        @JsonProperty("admin_area_1")
        private String adminArea1;

        @JsonProperty("postal_code")
        private String postalCode;

        @JsonProperty("country_code")
        private String countryCode;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payments {
        private List<Capture> captures;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Capture {
        private String id;
        private String status;
        private Amount amount;

        @JsonProperty("seller_protection")
        private SellerProtection sellerProtection;

        @JsonProperty("final_capture")
        private boolean finalCapture;

        @JsonProperty("disbursement_mode")
        private String disbursementMode;

        @JsonProperty("seller_receivable_breakdown")
        private SellerReceivableBreakdown sellerReceivableBreakdown;

        @JsonProperty("create_time")
        private String createTime;

        @JsonProperty("update_time")
        private String updateTime;

        private List<Link> links;
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
    public static class SellerProtection {
        private String status;

        @JsonProperty("dispute_categories")
        private List<String> disputeCategories;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SellerReceivableBreakdown {
        @JsonProperty("gross_amount")
        private Amount grossAmount;

        @JsonProperty("paypal_fee")
        private Amount paypalFee;

        @JsonProperty("net_amount")
        private Amount netAmount;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payer {
        private Name name;

        @JsonProperty("email_address")
        private String emailAddress;

        @JsonProperty("payer_id")
        private String payerId;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Name {
        @JsonProperty("given_name")
        private String givenName;
        private String surname;
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

