package com.crochet.spring.jpa.demo.controller.ghn;

import com.crochet.spring.jpa.demo.dto.ghn.address.GHNDistrictDTO;
import com.crochet.spring.jpa.demo.dto.ghn.address.GHNGetWardDTO;
import com.crochet.spring.jpa.demo.dto.ghn.address.GHNProvinceDTO;
import com.crochet.spring.jpa.demo.dto.ghn.order.GHNOrderCreationRequest;
import com.crochet.spring.jpa.demo.dto.ghn.order.GHNOrderCreationResponse;
import com.crochet.spring.jpa.demo.dto.ghn.store.GHNShopResponse;
import com.crochet.spring.jpa.demo.dto.ghn.store.GHNShopRetrievalRequest;
import com.crochet.spring.jpa.demo.service.ghn.GHNService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/GHN")
public class GHNController {
    @Autowired
    private GHNService ghnService;
    @Autowired
    private Gson gson;

    @PostMapping("/shop/all")
    public ResponseEntity<GHNShopResponse> getShopAll(@RequestBody GHNShopRetrievalRequest request) {
        String result = ghnService.getShopAll(request);
        var response = gson.fromJson(result, GHNShopResponse.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/province")
    public ResponseEntity<GHNProvinceDTO> getProvince() {
        String result = ghnService.getProvince();
        var response = gson.fromJson(result, GHNProvinceDTO.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/district")
    public ResponseEntity<GHNDistrictDTO> getDistrict(@RequestParam("province_id") String id) {
        String result = ghnService.getDistrict(id);
        var response = gson.fromJson(result, GHNDistrictDTO.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ward")
    public ResponseEntity<GHNGetWardDTO> getWard(@RequestParam("district_id") String id) {
        String result = ghnService.getWard(id);
        var response = gson.fromJson(result, GHNGetWardDTO.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/order/create")
    public ResponseEntity<GHNOrderCreationResponse> createOrder(@RequestBody GHNOrderCreationRequest request) {
        String result = ghnService.createOrder(request);
        var payload = gson.fromJson(result, GHNOrderCreationResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(payload);
    }

    @PostMapping("/shop/info")
    public ResponseEntity<Map<String, String>> getShopInfo(@RequestBody GHNShopRetrievalRequest request) {
        return ResponseEntity.ok(ghnService.getAddressFromShop(request));
    }
}
