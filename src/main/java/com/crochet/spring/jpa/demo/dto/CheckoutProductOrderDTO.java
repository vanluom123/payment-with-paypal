package com.crochet.spring.jpa.demo.dto;

import com.crochet.spring.jpa.demo.type.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CheckoutProductOrderDTO {
  private String note;
  private ContactDTO contact;
  private ShopDTO shop;
  private List<ProductDTO> products;
  private CustomerDTO customer;
  private PaymentMethod paymentMethod;
  private PaymentDTO payment;
}
