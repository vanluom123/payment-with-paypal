package com.crochet.spring.jpa.demo.payload.dto;

import com.crochet.spring.jpa.demo.type.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponseDTO {
    private String id;
    private OrderStatus status;
    private List<LinkDTO> links;
}
