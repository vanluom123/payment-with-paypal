package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.mapper.ProductMapper;
import com.crochet.spring.jpa.demo.model.Product;
import com.crochet.spring.jpa.demo.payload.request.ProductRequest;
import com.crochet.spring.jpa.demo.payload.request.UpdateProductRequest;
import com.crochet.spring.jpa.demo.payload.response.ProductResponse;
import com.crochet.spring.jpa.demo.repository.ProductRepo;
import com.crochet.spring.jpa.demo.service.CategoryService;
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
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductMapper productMapper;

    @Transactional
    @Override
    public ProductResponse create(ProductRequest request, MultipartFile[] files) {
        var category = categoryService.getCategoryById(request.getCategoryId());
        Product product = Product.builder()
                .category(category)
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .height(request.getHeight())
                .width(request.getWidth())
                .length(request.getLength())
                .weight(request.getWeight())
                .files(convertMultipartFileToString(files)).build();
        product = productRepo.save(product);
        return productMapper.toResponse(product);
    }

    @Transactional
    @Override
    public String update(UpdateProductRequest request, MultipartFile[] files) {
        var product = this.getById(request.getId());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setHeight(request.getHeight());
        product.setWidth(request.getWidth());
        product.setLength(request.getLength());
        product.setWeight(request.getWeight());
        product.setFiles(convertMultipartFileToString(files));
        return "Update success";
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
        return productMapper.toResponses(products);
    }

    @Override
    public Product getById(UUID uuid) {
        return productRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
