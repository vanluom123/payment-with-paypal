package com.crochet.spring.jpa.demo.payload.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CheckoutOrderProductResponse {
    private String note;
    private ContactResponse contact;
    private ShopResponse shop;
    private List<ProductResponse> products;
    private CustomerResponse customer;
}
