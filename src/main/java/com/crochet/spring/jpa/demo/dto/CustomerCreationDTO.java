package com.crochet.spring.jpa.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CustomerCreationDTO {
  private UUID id;
  private String name;
  private String address;
  private String email;
  private String phone;
  private String username;
  private String password;
}
