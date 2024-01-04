package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.payload.request.ProductRequest;
import com.crochet.spring.jpa.demo.payload.request.UpdateProductRequest;
import com.crochet.spring.jpa.demo.payload.response.ApiResponse;
import com.crochet.spring.jpa.demo.payload.response.ProductResponse;
import com.crochet.spring.jpa.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(value = "/update", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> update(
            @RequestParam(value = "product_id") UUID id,
            @RequestParam("name") String name,
            @RequestParam("description") String desc,
            @RequestParam("price") Double price,
            @RequestParam("height") int height,
            @RequestParam("width") int width,
            @RequestParam("length") int length,
            @RequestParam("weight") int weight,
            @RequestPart(required = false) MultipartFile[] files
    ) {
        var request = new UpdateProductRequest(id, name, price, desc, height, width, length, weight);
        var result = productService.update(request, files);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ApiResponse<ProductResponse>> create(
            @RequestParam(value = "category_id") UUID categoryId,
            @RequestParam("name") String name,
            @RequestParam("description") String desc,
            @RequestParam("price") Double price,
            @RequestParam("height") int height,
            @RequestParam("width") int width,
            @RequestParam("length") int length,
            @RequestParam("weight") int weight,
            @RequestPart(required = false) MultipartFile[] files
    ) {
        var request = new ProductRequest(categoryId, name, price, desc, height, width, length, weight);
        var result = ApiResponse.<ProductResponse>builder()
                .success(true)
                .message("Create product success")
                .result(productService.create(request, files))
                .build();
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAll() {
        var result = ApiResponse.<List<ProductResponse>>builder()
                .success(true)
                .message("Get success")
                .result(productService.getAll())
                .build();
        return ResponseEntity.ok(result);
    }
}
