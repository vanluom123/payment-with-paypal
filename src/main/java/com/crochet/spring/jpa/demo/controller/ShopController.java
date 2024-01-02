package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.payload.dto.CreateOrUpdateShopRequest;
import com.crochet.spring.jpa.demo.service.ShopService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop")
public class ShopController {
    @Autowired
    private Gson gson;
    @Autowired
    private ShopService shopService;

    @PostMapping("/create")
    public ResponseEntity<String> createShop(@RequestBody CreateOrUpdateShopRequest request) {
        var result = shopService.createOrUpdate(request);
        return ResponseEntity.status(201)
                .body(result);
    }
}
