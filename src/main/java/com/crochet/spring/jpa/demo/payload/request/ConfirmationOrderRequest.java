package com.crochet.spring.jpa.demo.payload.request;

import lombok.Data;

@Data
public class ConfirmationOrderRequest {
    private PaymentSource payment_source;

    @Data
    public static class PaymentSource {
        private PayPal paypal;
    }

    @Data
    public static class PayPal {
        private Name name;
        private String email_address;
        private ExperienceContext experience_context;

    }

    @Data
    public static class Name {
        private String given_name;
        private String surname;

    }

    @Data
    public static class ExperienceContext {
        private String payment_method_preference;
        private String brand_name;
        private String locale;
        private String landing_page;
        private String shipping_preference;
        private String user_action;
        private String return_url;
        private String cancel_url;
    }
}
