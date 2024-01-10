package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.dto.CustomerCreationDTO;
import com.crochet.spring.jpa.demo.dto.CustomerDTO;
import com.crochet.spring.jpa.demo.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerDTO save(CustomerCreationDTO request);

    List<CustomerDTO> getAll();

    List<Customer> findAll();

    Customer getCustomerById(UUID id);
}
