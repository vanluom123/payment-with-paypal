package com.crochet.spring.jpa.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderCreationDTO {
    @NotBlank(message = "CustomerId cannot be blank")
    @NotNull(message = "CustomerId cannot be null")
    private UUID customerId;

    @NotBlank(message = "ProductId cannot be blank")
    @NotNull(message = "ProductId cannot be null")
    private UUID productId;

    private int quantity;
}
