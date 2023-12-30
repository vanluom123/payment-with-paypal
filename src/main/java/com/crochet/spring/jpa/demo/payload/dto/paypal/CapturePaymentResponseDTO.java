package com.crochet.spring.jpa.demo.payload.dto.paypal;

import com.crochet.spring.jpa.demo.type.PaymentStatus;
import lombok.Data;

@Data
public class CapturePaymentResponseDTO {
    private PaymentStatus status;
}
