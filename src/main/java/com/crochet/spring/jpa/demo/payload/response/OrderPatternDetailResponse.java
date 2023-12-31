package com.crochet.spring.jpa.demo.payload.response;

import com.crochet.spring.jpa.demo.type.OrderStatus;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OrderPatternDetailResponse {
    @SerializedName("transaction_id")
    private String transactionId;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @SerializedName("order_date")
    private Date orderDate;
    @SerializedName("redirect_url")
    private String redirectUrl;
}
