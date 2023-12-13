package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.payload.request.ProductRequest;
import com.crochet.spring.jpa.demo.payload.result.ApiResult;
import com.crochet.spring.jpa.demo.payload.result.ProductResult;
import com.crochet.spring.jpa.demo.service.contact.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<ApiResult<ProductResult>> saveProduct(@RequestBody ProductRequest request) {
        var result = ApiResult.<ProductResult>builder()
                .message("Create product success")
                .result(productService.saveProduct(request))
                .build();
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<ApiResult<List<ProductResult>>> getAll() {
        var result = ApiResult.<List<ProductResult>>builder()
                .message("Get success")
                .result(productService.getAll())
                .build();
        return ResponseEntity.ok(result);
    }
}
