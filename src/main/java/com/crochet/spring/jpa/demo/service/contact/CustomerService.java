package com.crochet.spring.jpa.demo.service.contact;

import com.crochet.spring.jpa.demo.payload.request.CustomerRequest;
import com.crochet.spring.jpa.demo.payload.result.CustomerResult;

import java.util.List;

public interface CustomerService {
    CustomerResult save(CustomerRequest request);

    List<CustomerResult> getAll();
}
