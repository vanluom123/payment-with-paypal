package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.payload.request.CustomerRequest;
import com.crochet.spring.jpa.demo.payload.response.CustomerResponse;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerResponse save(CustomerRequest request);

    List<CustomerResponse> getAll();

    Customer getCustomerById(UUID id);
}
