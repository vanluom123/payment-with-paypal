package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.service.contact.GHTKService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xiaofeng.webclient.service.WebClientService;

@Service
public class GHTKServiceImpl implements GHTKService {
    private final WebClientService webClientService;

    public GHTKServiceImpl(@Value("${ghtk.token}") String token,
                           WebClientService webClientService) {
        webClientService.builder()
                .defaultHeaders(httpHeaders -> httpHeaders.setBearerAuth(token));
        this.webClientService = webClientService;
    }

    
}
