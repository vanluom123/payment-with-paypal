package com.crochet.spring.jpa.demo.payload.dto;

import com.crochet.spring.jpa.demo.type.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponseDTO {
    private String id;
    private OrderStatus status;
    private List<LinkDTO> links;
}
