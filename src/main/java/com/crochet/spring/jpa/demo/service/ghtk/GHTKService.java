package com.crochet.spring.jpa.demo.service.ghtk;

import com.crochet.spring.jpa.demo.payload.dto.ghtk.GHTKCreateOrder;

public interface GHTKService {
    String createGHTKOrder(GHTKCreateOrder ghtkCreateOrder);
}
