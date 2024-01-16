package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.dto.ShopDTO;
import com.crochet.spring.jpa.demo.dto.ghn.store.GHNShopCreationRequest;
import com.crochet.spring.jpa.demo.mapper.ShopMapper;
import com.crochet.spring.jpa.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shop")
public class ShopController {
  @Autowired
  private ShopService shopService;
  @Autowired
  private ShopMapper mapper;

  @PostMapping("/create")
  public ResponseEntity<String> createShop(
      @RequestParam(required = false) UUID id,
      @RequestParam UUID customerId,
      @RequestParam("shop_name") String shopName,
      @RequestParam String phone,
      @RequestParam String address,
      @RequestParam("ward_code") String wardCode,
      @RequestParam("ward_name") String wardName,
      @RequestParam("district_id") int districtId,
      @RequestParam("district_name") String districtName,
      @RequestParam("province_name") String provinceName
  ) {
    GHNShopCreationRequest request = GHNShopCreationRequest.builder()
        .id(id)
        .customerId(customerId)
        .shopName(shopName)
        .phone(phone)
        .address(address)
        .wardCode(wardCode)
        .wardName(wardName)
        .districtId(districtId)
        .districtName(districtName)
        .provinceName(provinceName)
        .build();
    var result = shopService.createOrUpdate(request);
    return ResponseEntity.status(201)
        .body(result);
  }

  @GetMapping
  public ResponseEntity<List<ShopDTO>> getAll() {
    var shops = shopService.getAll();
    var results = mapper.toDTOs(shops);
    return ResponseEntity.ok(results);
  }
}
