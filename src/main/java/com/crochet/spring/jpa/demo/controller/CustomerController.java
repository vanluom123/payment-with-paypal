package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.payload.dto.CustomerDTO;
import com.crochet.spring.jpa.demo.payload.request.CustomerRequest;
import com.crochet.spring.jpa.demo.payload.response.ApiResponse;
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
    public ResponseEntity<CustomerDTO> save(@RequestBody CustomerRequest request) {
        CustomerDTO result = customerService.save(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerDTO>>> getAll() {
        var cus = customerService.getAll();
        var result = ApiResponse.<List<CustomerDTO>>builder()
                .success(true)
                .message("Get success")
                .data(cus)
                .build();
        return ResponseEntity.ok(result);
    }
}
