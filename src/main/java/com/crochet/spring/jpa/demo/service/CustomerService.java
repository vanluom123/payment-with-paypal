package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.payload.dto.CustomerDTO;
import com.crochet.spring.jpa.demo.payload.request.CustomerRequest;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerDTO save(CustomerRequest request);

    List<CustomerDTO> getAll();

    List<Customer> findAll();

    Customer getCustomerById(UUID id);
}
