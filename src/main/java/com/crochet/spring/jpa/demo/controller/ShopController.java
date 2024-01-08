package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.mapper.ShopMapper;
import com.crochet.spring.jpa.demo.payload.dto.ghn.store.CreateOrUpdateShopRequest;
import com.crochet.spring.jpa.demo.payload.response.ShopResponse;
import com.crochet.spring.jpa.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<String> createShop(@RequestBody CreateOrUpdateShopRequest request) {
        var result = shopService.createOrUpdate(request);
        return ResponseEntity.status(201)
                .body(result);
    }

    @GetMapping
    public ResponseEntity<List<ShopResponse>> getAll() {
        var shops = shopService.getAll();
        var results = mapper.toResponses(shops);
        return ResponseEntity.ok(results);
    }
}
