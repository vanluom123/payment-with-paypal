package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.model.Contact;
import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.payload.request.ContactRequest;
import com.crochet.spring.jpa.demo.repository.ContactRepo;
import com.crochet.spring.jpa.demo.service.ContactService;
import com.crochet.spring.jpa.demo.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private CustomerService customerService;

    @Transactional
    @Override
    public String createContact(ContactRequest request) {
        Contact contact;
        if (request.getContactId() == null) {
            contact = new Contact();
            var customer = customerService.getCustomerById(request.getCustomerId());
            contact.setCustomer(customer);
        } else {
            contact = this.getDetail(request.getContactId());
        }
        contact.setName(request.getName());
        contact.setAddress(request.getAddress());
        contact.setPhone(request.getPhone());
        contact.setWardCode(request.getWardCode());
        contact.setWardName(request.getWardName());
        contact.setDistrictID(request.getDistrictID());
        contact.setDistrictName(request.getDistrictName());
        contact.setProvinceName(request.getProvinceName());
        contact.setDefault(request.isDefault());
        contactRepo.save(contact);
        return "Add contact success";
    }

    @Override
    public Contact getDetail(UUID id) {
        return contactRepo.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    @Override
    public List<Contact> getContactsByCustomer(UUID customerId) {
        return contactRepo.findByCustomer(customerId)
                .orElseThrow(() -> new RuntimeException("Customer has not a contact"));
    }

    @Override
    public String setContactDefault(UUID customerId, UUID contactId) {
        var contacts = getContactsByCustomer(customerId);
        for (var contact : contacts) {
            contact.setDefault(contact.getId().equals(contactId));
        }
        contactRepo.saveAll(contacts);
        return "Change default success";
    }

    @Override
    public Contact findDefaultContact(Customer customer) {
        return customer.getContacts()
                .stream()
                .filter(Contact::isDefault)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Default contact not found"));
    }
}
