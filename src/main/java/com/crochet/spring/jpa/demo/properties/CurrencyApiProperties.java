package com.crochet.spring.jpa.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "currencyapi")
public class CurrencyApiProperties {
    private String apikey;
}
