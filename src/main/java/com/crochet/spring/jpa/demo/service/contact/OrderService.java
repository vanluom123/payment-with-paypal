package com.crochet.spring.jpa.demo.service.contact;

import com.crochet.spring.jpa.demo.payload.request.OrderRequest;
import com.crochet.spring.jpa.demo.payload.result.OrderResult;

public interface OrderService {
    OrderResult orderProduct(OrderRequest request);
}
