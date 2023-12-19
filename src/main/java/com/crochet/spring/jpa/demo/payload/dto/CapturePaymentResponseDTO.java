package com.crochet.spring.jpa.demo.payload.dto;

import com.crochet.spring.jpa.demo.type.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class CapturePaymentResponseDTO {
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
