package com.crochet.spring.jpa.demo.service.ghtk;

import com.crochet.spring.jpa.demo.payload.dto.ghtk.GHTKCreateOrder;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xiaofeng.webclient.service.WebClientService;
import org.xiaofeng.webclient.type.HttpMethod;

@Service
public class GHTKServiceImpl implements GHTKService {
    private final String token;

    private final WebClientService webClientService;
    private final Gson gson;

    public GHTKServiceImpl(@Value("${ghtk.token}") String token,
                           WebClientService webClientService,
                           Gson gson) {
        this.token = token;
        this.gson = gson;
        this.webClientService = webClientService;
    }

    @Override
    public String createGHTKOrder(GHTKCreateOrder ghtkCreateOrder) {
        String url = "https://khachhang-staging.ghtklab.com/services/shipment/order/?ver=1.5";
        String payload = gson.toJson(ghtkCreateOrder);
        var result = webClientService.invokeApi(url,
                HttpMethod.POST,
                payload,
                httpHeaders -> httpHeaders.add("Token", token)
        ).block();
        return result;
    }

    public void auth() {
        String url = "services.giaohangtietkiem.vn/authentication-request-sample";
    }
}
