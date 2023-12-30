package com.crochet.spring.jpa.demo.controller.ghn;

import com.crochet.spring.jpa.demo.payload.dto.ghn.GetShopsRequestDTO;
import com.crochet.spring.jpa.demo.payload.dto.ghn.ShopResponseDTO;
import com.crochet.spring.jpa.demo.service.ghn.GHNService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/GHN")
public class GHNController {
    @Autowired
    private GHNService ghnService;
    @Autowired
    private Gson gson;

    @PostMapping("/shop/all")
    public ResponseEntity<ShopResponseDTO> getShopAll(@RequestBody GetShopsRequestDTO request) {
        String result = ghnService.getShopAll(request);
        var response = gson.fromJson(result, ShopResponseDTO.class);
        return ResponseEntity.ok(response);
    }
}
