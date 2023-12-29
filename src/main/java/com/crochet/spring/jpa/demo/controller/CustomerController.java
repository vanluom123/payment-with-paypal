package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.payload.request.CustomerRequest;
import com.crochet.spring.jpa.demo.payload.response.ApiResponse;
import com.crochet.spring.jpa.demo.payload.response.CustomerResponse;
import com.crochet.spring.jpa.demo.service.contact.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<CustomerResponse> save(@RequestBody CustomerRequest request) {
        CustomerResponse result = customerService.save(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAll() {
        var cus = customerService.getAll();
        var result = ApiResponse.<List<CustomerResponse>>builder()
                .success(true)
                .message("Get success")
                .result(cus)
                .build();
        return ResponseEntity.ok(result);
    }
}
