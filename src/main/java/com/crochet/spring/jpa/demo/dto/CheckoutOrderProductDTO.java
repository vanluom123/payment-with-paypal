package com.crochet.spring.jpa.demo.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CheckoutOrderProductDTO {
    private String note;
    @SerializedName("payment_method")
    private int paymentMethod;
    private ContactDTO contact;
    private ShopDTO shop;
    private List<ProductDTO> products;
    private CustomerDTO customer;
}
