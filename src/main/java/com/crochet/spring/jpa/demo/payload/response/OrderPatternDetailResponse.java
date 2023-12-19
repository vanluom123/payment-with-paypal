package com.crochet.spring.jpa.demo.payload.response;

import com.crochet.spring.jpa.demo.type.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OrderPatternDetailResponse {
    @JsonProperty("transaction_id")
    private String transactionId;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @JsonProperty("order_date")
    private Date orderDate;
    @JsonProperty("redirect_url")
    private String redirectUrl;
}
