package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.dto.ExchangeRateDTO;
import com.crochet.spring.jpa.demo.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<ExchangeRateDTO> convertCurrency(
            @RequestParam("base_currency") String baseCurrency,
            @RequestParam("currencies") String currencies
    ) {
        return ResponseEntity.ok(currencyService.convertCurrency(baseCurrency, currencies));
    }
}
