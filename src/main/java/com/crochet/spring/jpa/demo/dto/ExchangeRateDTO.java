package com.crochet.spring.jpa.demo.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRateDTO {
    private Meta meta;
    private Map<String, CurrencyData> data;

    @Data
    public static class Meta {
        private String last_updated_at;
    }

    @Data
    public static class CurrencyData {
        private String code;
        private Double value;
    }
}
