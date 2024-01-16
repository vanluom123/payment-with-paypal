package com.crochet.spring.jpa.demo.dto;

import com.crochet.spring.jpa.demo.type.OrderStatus;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PatternOrderDetailDTO {
  @SerializedName("transaction_id")
  private String transactionId;
  private OrderStatus status;
  @SerializedName("order_date")
  private Date orderDate;
  @SerializedName("redirect_url")
  private String redirectUrl;
}
