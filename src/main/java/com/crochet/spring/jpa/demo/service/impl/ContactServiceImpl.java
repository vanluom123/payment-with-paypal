package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.model.Contact;
import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.payload.request.CreateContactRequest;
import com.crochet.spring.jpa.demo.repository.ContactRepo;
import com.crochet.spring.jpa.demo.service.ContactService;
import com.crochet.spring.jpa.demo.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private CustomerService customerService;

    @Transactional
    @Override
    public String createContact(CreateContactRequest request) {
        var customer = customerService.getCustomerById(request.getCustomerId());
        var contact = new Contact(request.getAddress(),
                request.getPhone(),
                request.getWardCode(),
                request.getWardName(),
                request.getDistrictID(),
                request.getDistrictName(),
                request.getProvinceName(),
                customer);
        contactRepo.save(contact);
        return "Add contact success";
    }

    @Override
    public Contact getDetail(UUID id) {
        return contactRepo.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    @Override
    public Contact getContactByCustomer(Customer customer) {
        return contactRepo.findByCustomer(customer)
                .orElseThrow(() -> new RuntimeException("Customer has not a contact"));
    }
}
