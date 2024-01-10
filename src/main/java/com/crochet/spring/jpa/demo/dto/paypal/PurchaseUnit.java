package com.crochet.spring.jpa.demo.dto.paypal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseUnit {
    private MoneyDTO amount;
}
