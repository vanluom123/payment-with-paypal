package com.crochet.spring.jpa.demo.dto.paypal;

import com.crochet.spring.jpa.demo.type.paypal.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponseDTO {
    private String id;
    private PaymentStatus status;
    private List<LinkDTO> links;
}
