package com.crochet.spring.jpa.demo.service.contact;

import com.crochet.spring.jpa.demo.payload.request.OrderRequest;
import com.crochet.spring.jpa.demo.payload.response.OrderProductDetailResponse;

public interface OrderProductDetailService {
    OrderProductDetailResponse orderProduct(OrderRequest request);
}
