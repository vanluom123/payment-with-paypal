package com.crochet.spring.jpa.demo.service.contact;

import com.crochet.spring.jpa.demo.payload.request.ProductRequest;
import com.crochet.spring.jpa.demo.payload.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse saveProduct(ProductRequest request);

    List<ProductResponse> getAll();
}
