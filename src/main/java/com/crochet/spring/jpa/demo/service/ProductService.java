package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.payload.request.ProductRequest;
import com.crochet.spring.jpa.demo.payload.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    //    @Transactional
    ProductResponse saveProduct(ProductRequest request, MultipartFile[] files);

    List<ProductResponse> getAll();
}
