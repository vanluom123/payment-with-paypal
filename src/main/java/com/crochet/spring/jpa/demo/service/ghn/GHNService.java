package com.crochet.spring.jpa.demo.service.ghn;

import com.crochet.spring.jpa.demo.payload.dto.ghn.GetShopsRequestDTO;

public interface GHNService {
    String getShopAll(GetShopsRequestDTO getShopsRequest);
}
