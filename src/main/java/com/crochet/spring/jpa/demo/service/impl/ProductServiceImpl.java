package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.mapper.ProductMapper;
import com.crochet.spring.jpa.demo.model.Product;
import com.crochet.spring.jpa.demo.payload.request.ProductRequest;
import com.crochet.spring.jpa.demo.payload.response.ProductResponse;
import com.crochet.spring.jpa.demo.repository.ProductRepo;
import com.crochet.spring.jpa.demo.service.CategoryService;
import com.crochet.spring.jpa.demo.service.ProductService;
import com.crochet.spring.jpa.demo.service.ShopService;
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
    @Autowired
    private ShopService shopService;

    @Transactional
    @Override
    public ProductResponse createOrUpdate(ProductRequest request, MultipartFile[] files) {
        Product product;
        if (request.getProductId() == null) {
            product = new Product();
            var category = categoryService.getCategoryById(request.getCategoryId());
            var shop = shopService.getById(request.getShopId());
            product.setCategory(category);
            product.setShop(shop);
        } else {
            product = this.getById(request.getProductId());
        }
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setHeight(request.getHeight());
        product.setWidth(request.getWidth());
        product.setLength(request.getLength());
        product.setWeight(request.getWeight());
        product.setFiles(convertMultipartFileToString(files));
        product = productRepo.save(product);
        return productMapper.toResponse(product);
    }

    @SneakyThrows
    private List<String> convertMultipartFileToString(MultipartFile[] files) {
        if (ObjectUtils.isEmpty(files)) {
            return null;
        }
        List<String> urls = new ArrayList<>();
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
