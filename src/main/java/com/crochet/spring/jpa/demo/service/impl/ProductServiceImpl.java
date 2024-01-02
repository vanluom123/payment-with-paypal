package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.mapper.ProductMapper;
import com.crochet.spring.jpa.demo.model.Product;
import com.crochet.spring.jpa.demo.payload.request.ProductRequest;
import com.crochet.spring.jpa.demo.payload.response.ProductResponse;
import com.crochet.spring.jpa.demo.repository.ProductRepo;
import com.crochet.spring.jpa.demo.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Transactional
    @Override
    public ProductResponse saveProduct(ProductRequest request, MultipartFile[] files) {
        Product product;
        if (request.getId() == null) {
            // Create product
            product = Product.builder()
                    .name(request.getName())
                    .price(request.getPrice())
                    .description(request.getDescription())
                    .files(convertMultipartFileToString(files)).build();
        } else {
            // Update product
            product = productRepo.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setPrice(request.getPrice());
            product.setFiles(convertMultipartFileToString(files));
        }
        product = productRepo.save(product);
        return ProductMapper.INSTANCE.productToProductResult(product);
    }

    @SneakyThrows
    private List<String> convertMultipartFileToString(MultipartFile[] files) {
        List<String> urls = new ArrayList<>();
        if (ObjectUtils.isEmpty(files)) {
            return null;
        }
        for (var file : files) {
            urls.add(Base64.getEncoder().encodeToString(file.getBytes()));
        }
        return urls;
    }

    @Override
    public List<ProductResponse> getAll() {
        var products = productRepo.findAll();
        return ProductMapper.INSTANCE.productsToProductResults(products);
    }
}