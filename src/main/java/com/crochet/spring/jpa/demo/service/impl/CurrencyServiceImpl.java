package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.dto.ExchangeRateDTO;
import com.crochet.spring.jpa.demo.properties.CurrencyApiProperties;
import com.crochet.spring.jpa.demo.service.CurrencyService;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.xiaofeng.webclient.service.WebClientService;
import org.xiaofeng.webclient.type.HttpMethod;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    private WebClientService webClientService;
    @Autowired
    private CurrencyApiProperties currencyApiProps;
    @Autowired
    private Gson gson;

    @PostConstruct
    public void init() {
        webClientService.builder()
                .defaultHeaders(header -> header.add("apikey", currencyApiProps.getApikey()));
    }

    @Override
    public ExchangeRateDTO convertCurrency(String baseCurrency, String currencies) {
        var uriBuilderFactory = new DefaultUriBuilderFactory()
                .uriString("https://api.currencyapi.com/v3/latest")
                .queryParam("base_currency", baseCurrency)
                .queryParam("currencies", currencies)
                .build();
        String url = uriBuilderFactory.toString();
        String result = webClientService.invokeApi(url, HttpMethod.GET).block();
        var exchangeRate = gson.fromJson(result, ExchangeRateDTO.class);
        return exchangeRate;
    }
}
