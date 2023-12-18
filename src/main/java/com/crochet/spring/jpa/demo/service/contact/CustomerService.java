package com.crochet.spring.jpa.demo.service.contact;

import com.crochet.spring.jpa.demo.payload.request.CustomerRequest;
import com.crochet.spring.jpa.demo.payload.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse save(CustomerRequest request);

    List<CustomerResponse> getAll();
}
