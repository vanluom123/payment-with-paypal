package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.payload.request.CreateContactRequest;
import com.crochet.spring.jpa.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
            @RequestParam("customer_id") UUID customerId,
            @RequestParam("address") String address,
            @RequestParam("phone") String phone,
            @RequestParam("ward_code") int wardCode,
            @RequestParam("ward_name") String wardName,
            @RequestParam("district_id") int districtId,
            @RequestParam("district_name") String districtName,
            @RequestParam("province_name") String provinceName
    ) {
        var request = new CreateContactRequest(customerId,
                address,
                phone,
                wardCode,
                wardName,
                districtId,
                districtName,
                provinceName);
        var result = contactService.createContact(request);
        return ResponseEntity.status(201)
                .body(result);
    }
}
