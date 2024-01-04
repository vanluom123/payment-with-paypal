package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.model.OrderProductDetail;
import com.crochet.spring.jpa.demo.repository.OrderProductDetailRepo;
import com.crochet.spring.jpa.demo.service.OrderProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderProductDetailServiceImpl implements OrderProductDetailService {
    @Autowired
    private OrderProductDetailRepo orderProductDetailRepo;

    @Transactional
    @Override
    public void saveAll(List<OrderProductDetail> orderProductDetails) {
        orderProductDetailRepo.saveAll(orderProductDetails);
    }
}
