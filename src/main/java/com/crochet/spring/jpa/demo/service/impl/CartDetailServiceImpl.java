package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.repository.CartDetailRepo;
import com.crochet.spring.jpa.demo.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartDetailServiceImpl implements CartDetailService {
  @Autowired
  private CartDetailRepo cartDetailRepo;

  @Override
  public Integer getQuantityByProductId(UUID productId) {
    return cartDetailRepo.findQuantityByProductId(productId);
  }
}
