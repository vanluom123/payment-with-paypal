package com.crochet.spring.jpa.demo.service;

import java.util.UUID;

public interface CartDetailService {
  Integer getQuantityByProductId(UUID productId);
}
