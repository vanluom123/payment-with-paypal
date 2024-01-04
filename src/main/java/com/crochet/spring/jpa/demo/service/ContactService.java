package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.model.Contact;
import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.payload.request.CreateContactRequest;

import java.util.UUID;

public interface ContactService {
    String createContact(CreateContactRequest request);

    Contact getDetail(UUID id);

    Contact getContactByCustomer(Customer customer);
}
