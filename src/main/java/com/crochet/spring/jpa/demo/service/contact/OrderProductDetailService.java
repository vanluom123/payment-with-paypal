package com.crochet.spring.jpa.demo.service.contact;

import com.crochet.spring.jpa.demo.payload.request.OrderRequest;
import com.crochet.spring.jpa.demo.payload.response.OrderResponse;

public interface OrderProductDetailService {
    OrderResponse orderProduct(OrderRequest request);
}
