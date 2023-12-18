package com.crochet.spring.jpa.demo.payload.response;

import com.crochet.spring.jpa.demo.type.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderPatternDetailResponse {
    @JsonProperty("transaction_id")
    private String transactionId;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @JsonProperty("order_date")
    private Date orderDate;
    @JsonProperty("approval_link")
    private String approvalLink;
}
