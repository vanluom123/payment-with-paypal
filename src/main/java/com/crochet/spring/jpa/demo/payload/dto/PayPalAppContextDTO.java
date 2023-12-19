package com.crochet.spring.jpa.demo.payload.dto;

import com.crochet.spring.jpa.demo.type.PaymentLandingPage;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayPalAppContextDTO {
    @SerializedName("brand_name")
    private String brandName;
    @SerializedName("landing_page")
    private PaymentLandingPage landingPage;
    @SerializedName("return_url")
    private String returnUrl;
    @SerializedName("cancel_url")
    private String cancelUrl;
}
