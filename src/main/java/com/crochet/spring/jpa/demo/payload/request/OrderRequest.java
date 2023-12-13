package com.crochet.spring.jpa.demo.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {
    @NotBlank(message = "CustomerId cannot be blank")
    @NotNull(message = "CustomerId cannot be null")
    private String customerId;

    @NotBlank(message = "ProductId cannot be blank")
    @NotNull(message = "ProductId cannot be null")
    private String productId;

    private int quantity;
}
