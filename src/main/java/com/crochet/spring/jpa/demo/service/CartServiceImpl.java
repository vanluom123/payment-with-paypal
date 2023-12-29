package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.repository.CartRepo;
import com.crochet.spring.jpa.demo.service.contact.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepo cartRepo;

    @Override
    public String addProductToCart(String customerId, String productId, int quantity) {
        cartRepo.addProductToCart(UUID.fromString(customerId), UUID.fromString(productId), quantity);
        return "Added to cart";
    }
}
