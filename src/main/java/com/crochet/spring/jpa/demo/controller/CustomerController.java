package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.dto.CustomerCreationDTO;
import com.crochet.spring.jpa.demo.dto.CustomerDTO;
import com.crochet.spring.jpa.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerCreationDTO request) {
        CustomerDTO result = customerService.save(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll() {
        var cus = customerService.getAll();
        return ResponseEntity.ok(cus);
    }
}
