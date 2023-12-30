package com.crochet.spring.jpa.demo.payload.dto.paypal;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MoneyDTO {
    @SerializedName("currency_code")
    private String currencyCode;
    private String value;
}
