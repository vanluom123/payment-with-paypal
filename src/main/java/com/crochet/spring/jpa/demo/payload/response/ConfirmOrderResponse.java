package com.crochet.spring.jpa.demo.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfirmOrderResponse {

    private String id;
    private String status;

    @JsonProperty("payment_source")
    private PaymentSource paymentSource;

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
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payer {
        private Name name;

        @JsonProperty("email_address")
        private String emailAddress;
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

