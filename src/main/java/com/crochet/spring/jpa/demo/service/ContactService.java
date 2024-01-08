package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.model.Contact;
import com.crochet.spring.jpa.demo.payload.request.CreateContactRequest;

import java.util.List;
import java.util.UUID;

public interface ContactService {
    String createContact(CreateContactRequest request);

    Contact getDetail(UUID id);

    List<Contact> getContactsByCustomer(UUID customerId);

    String setContactDefault(UUID customerId, UUID contactId);
}
