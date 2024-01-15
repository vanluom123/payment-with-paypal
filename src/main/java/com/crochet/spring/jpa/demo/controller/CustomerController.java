package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.dto.CustomerCreationDTO;
import com.crochet.spring.jpa.demo.dto.CustomerDTO;
import com.crochet.spring.jpa.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
  @Autowired
  private CustomerService customerService;

  @PostMapping("/save")
  public ResponseEntity<CustomerDTO> create(
      @RequestParam(required = false) UUID id,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String address,
      @RequestParam(required = false) String email,
      @RequestParam(required = false) String phone,
      @RequestParam(required = false) String username,
      @RequestParam(required = false) String password
  ) {
    CustomerCreationDTO request = CustomerCreationDTO.builder()
        .id(id)
        .name(name)
        .address(address)
        .email(email)
        .phone(phone)
        .username(username)
        .password(password)
        .build();
    CustomerDTO result = customerService.save(request);
    return ResponseEntity.ok(result);
  }

  @GetMapping
  public ResponseEntity<List<CustomerDTO>> getAll() {
    var cus = customerService.getAll();
    return ResponseEntity.ok(cus);
  }
}
