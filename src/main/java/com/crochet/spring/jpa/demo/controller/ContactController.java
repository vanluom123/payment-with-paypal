package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.payload.request.ContactRequest;
import com.crochet.spring.jpa.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/create")
    public ResponseEntity<String> createContact(
            @RequestParam(value = "contact_id", required = false) UUID contactId,
            @RequestParam(value = "customer_id", required = false) UUID customerId,
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("phone") String phone,
            @RequestParam("ward_code") String wardCode,
            @RequestParam("ward_name") String wardName,
            @RequestParam("district_id") int districtId,
            @RequestParam("district_name") String districtName,
            @RequestParam("province_name") String provinceName
    ) {
        var request = new ContactRequest(contactId,
                customerId,
                name,
                address,
                phone,
                wardCode,
                wardName,
                districtId,
                districtName,
                provinceName,
                false);
        var result = contactService.createContact(request);
        return ResponseEntity.status(201)
                .body(result);
    }

    @PutMapping("/makeDefault")
    public ResponseEntity<String> setDefault(
            @RequestParam("customer_id") UUID customerId,
            @RequestParam("contact_id") UUID contactId
    ) {
        var result = contactService.setContactDefault(customerId, contactId);
        return ResponseEntity.ok(result);
    }
}
