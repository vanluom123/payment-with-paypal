package com.crochet.spring.jpa.demo.dto.paypal;

import com.crochet.spring.jpa.demo.type.paypal.PaymentStatus;
import lombok.Data;

@Data
public class PaymentCaptureResponseDTO {
    private PaymentStatus status;
}
