package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.dto.ExchangeRateDTO;

public interface CurrencyService {
    ExchangeRateDTO convertCurrency(String baseCurrency, String currencies);
}
