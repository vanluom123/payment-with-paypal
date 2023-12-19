package com.crochet.spring.jpa.demo.payload.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseUnit {
    private MoneyDTO amount;
}
