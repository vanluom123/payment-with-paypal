package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.model.Product;
import com.crochet.spring.jpa.demo.payload.request.ProductRequest;
import com.crochet.spring.jpa.demo.payload.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponse createOrUpdate(ProductRequest request, MultipartFile[] files);

    List<ProductResponse> getAll();

    Product getById(UUID uuid);
}
