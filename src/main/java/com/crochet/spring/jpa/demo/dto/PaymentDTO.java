package com.crochet.spring.jpa.demo.dto;

import com.crochet.spring.jpa.demo.type.paypal.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
  private String transactionId;
  private double amount;
  private Date paymentDate;
  private PaymentStatus paymentStatus;
}
