package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.model.Cart;
import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.repository.CartRepo;
import com.crochet.spring.jpa.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepo cartRepo;

    @Override
    public String addProductToCart(UUID customerId,
                                   UUID productId,
                                   int quantity) {
        cartRepo.addProductToCart(customerId, productId, quantity);
        return "Added to cart";
    }

    @Override
    public List<Cart> getAllCartByCustomer(Customer customer) {
        return cartRepo.findAllCartByCustomer(customer);
    }

    @Transactional
    @Override
    public void deleteAll(List<Cart> carts) {
        cartRepo.deleteAll(carts);
    }

    @Override
    public Integer getQuantityByProductId(String productId) {
        return cartRepo.findQuantityByProductId(UUID.fromString(productId));
    }
}