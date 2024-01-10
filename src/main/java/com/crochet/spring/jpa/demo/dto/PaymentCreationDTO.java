package com.crochet.spring.jpa.demo.dto;

import com.crochet.spring.jpa.demo.type.paypal.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PaymentCreationDTO {
    private String transactionId;
    private Double amount;
    private Date paymentDate;
    private PaymentStatus paymentStatus;
}