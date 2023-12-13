package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.payload.request.CustomerRequest;
import com.crochet.spring.jpa.demo.payload.result.ApiResult;
import com.crochet.spring.jpa.demo.payload.result.CustomerResult;
import com.crochet.spring.jpa.demo.service.contact.CustomerService;
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
    public ResponseEntity<CustomerResult> save(@RequestBody CustomerRequest request) {
        CustomerResult result = customerService.save(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<ApiResult<List<CustomerResult>>> getAll() {
        var cus = customerService.getAll();
        var result = ApiResult.<List<CustomerResult>>builder()
                .message("Get success")
                .result(cus)
                .build();
        return ResponseEntity.ok(result);
    }
}
