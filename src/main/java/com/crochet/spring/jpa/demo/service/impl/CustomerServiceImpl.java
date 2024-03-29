package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.dto.CustomerCreationDTO;
import com.crochet.spring.jpa.demo.dto.CustomerDTO;
import com.crochet.spring.jpa.demo.mapper.CustomerMapper;
import com.crochet.spring.jpa.demo.model.Customer;
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
    @Autowired
    private CustomerMapper customerMapper;

    @Transactional
    @Override
    public CustomerDTO save(CustomerCreationDTO request) {
        Customer customer;
        if (request.getId() == null) {
            customer = new Customer();
        } else {
            customer = getCustomerById(request.getId());
        }

        if (invalidCustomer(customer)) {
            throw new RuntimeException("Customer already exist");
        }

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setUsername(request.getUsername());
        customer.setPassword(request.getPassword());
        customer = customerRepo.save(customer);
        return customerMapper.toDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAll() {
        var cus = this.findAll();
        return customerMapper.toDTOs(cus);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
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
