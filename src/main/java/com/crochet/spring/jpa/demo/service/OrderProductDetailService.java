package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.model.OrderProductDetail;

import java.util.List;

public interface OrderProductDetailService {
    void saveAll(List<OrderProductDetail> orderProductDetails);
}
