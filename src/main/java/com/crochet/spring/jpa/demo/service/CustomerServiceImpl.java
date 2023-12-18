package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.mapper.CustomerMapper;
import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.payload.response.CustomerResponse;
import com.crochet.spring.jpa.demo.repository.CustomerRepository;
import com.crochet.spring.jpa.demo.payload.request.CustomerRequest;
import com.crochet.spring.jpa.demo.service.contact.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

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
            customer = customerRepository.findById(UUID.fromString(request.getId()))
                    .orElseThrow(() -> new RuntimeException("Cannot found customer"));
            CustomerMapper.INSTANCE.partialUpdate(request, customer);
        }

        if (invalidCustomer(customer)) {
            throw new RuntimeException("Customer already exist");
        }

        customer = customerRepository.save(customer);
        return CustomerMapper.INSTANCE.customerToCustomerResult(customer);
    }

    @Override
    public List<CustomerResponse> getAll() {
        var cus = customerRepository.findAll();
        return CustomerMapper.INSTANCE.toResults(cus);
    }

    private boolean invalidCustomer(Customer existCustomer) {
        var customers = customerRepository.findAll();
        return customers.stream()
                .anyMatch(customer -> customer.equals(existCustomer));
    }
}
