package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.repository.OrderProductRepo;
import com.crochet.spring.jpa.demo.service.contact.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProductServiceImpl implements OrderProductService {
    @Autowired
    private OrderProductRepo orderProductRepo;

    public String addProductToCart(String customerId, String productId) {
        return "Added to cart";
    }
}
