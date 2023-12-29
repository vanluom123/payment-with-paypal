package com.crochet.spring.jpa.demo.controller.ghtk;

import com.crochet.spring.jpa.demo.payload.dto.ghtk.GHTKCreateOrder;
import com.crochet.spring.jpa.demo.payload.dto.ghtk.GHTKCreateOrderResponse;
import com.crochet.spring.jpa.demo.service.ghtk.GHTKService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/GHTK/orders")
public class GHTKCreateOrderController {
    @Autowired
    private GHTKService ghtkService;

    @Autowired
    private Gson gson;

    @PostMapping("/create")
    public ResponseEntity<GHTKCreateOrderResponse> createOrder(@RequestBody GHTKCreateOrder ghtkCreateOrder) {
        var result = ghtkService.createGHTKOrder(ghtkCreateOrder);
        var response = gson.fromJson(result, GHTKCreateOrderResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
