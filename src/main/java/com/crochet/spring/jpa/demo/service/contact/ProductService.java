package com.crochet.spring.jpa.demo.service.contact;

import com.crochet.spring.jpa.demo.payload.request.ProductRequest;
import com.crochet.spring.jpa.demo.payload.result.ProductResult;

import java.util.List;

public interface ProductService {
    ProductResult saveProduct(ProductRequest request);

    List<ProductResult> getAll();
}
