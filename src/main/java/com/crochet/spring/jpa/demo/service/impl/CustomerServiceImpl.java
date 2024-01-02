package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.mapper.CustomerMapper;
import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.payload.request.CustomerRequest;
import com.crochet.spring.jpa.demo.payload.response.CustomerResponse;
import com.crochet.spring.jpa.demo.repository.CustomerRepo;
import com.crochet.spring.jpa.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Transactional
    @Override
    public CustomerResponse save(CustomerRequest request) {
        Customer customer;
        if (request.getId() == null) {
            customer = Customer.builder()
                    .name(request.getName())
                    .address(request.getAddress())
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .build();
        } else {
            customer = customerRepo.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("Cannot found customer"));
            CustomerMapper.INSTANCE.partialUpdate(request, customer);
        }

        if (invalidCustomer(customer)) {
            throw new RuntimeException("Customer already exist");
        }

        customer = customerRepo.save(customer);
        return CustomerMapper.INSTANCE.customerToCustomerResult(customer);
    }

    @Override
    public List<CustomerResponse> getAll() {
        var cus = customerRepo.findAll();
        return CustomerMapper.INSTANCE.toResults(cus);
    }

    private boolean invalidCustomer(Customer existCustomer) {
        var customers = customerRepo.findAll();
        return customers.stream()
                .anyMatch(customer -> customer.equals(existCustomer));
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Cannot found customer"));
    }
}
