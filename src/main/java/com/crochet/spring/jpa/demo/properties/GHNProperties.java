package com.crochet.spring.jpa.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "ghn")
public class GHNProperties {
    private String token;
    private String shopId;
    private String clientId;
}
