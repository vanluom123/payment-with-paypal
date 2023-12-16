package com.crochet.spring.jpa.demo.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.xiaofeng.webclient.module.WebClientModule;
import org.xiaofeng.webclient.service.WebClientService;
import org.xiaofeng.webclient.service.WebClientServiceImpl;

@Configuration
public class WebClientConfiguration {
    @Bean
    @Scope(scopeName = "prototype")
    public WebClientService webClientService() {
        Injector injector = Guice.createInjector(new WebClientModule());
        WebClientService service = injector.getInstance(WebClientServiceImpl.class);
        return service;
    }
}
