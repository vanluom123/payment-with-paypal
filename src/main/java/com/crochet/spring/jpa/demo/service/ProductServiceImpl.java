package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.mapper.ProductMapper;
import com.crochet.spring.jpa.demo.model.Product;
import com.crochet.spring.jpa.demo.repository.ProductRepository;
import com.crochet.spring.jpa.demo.payload.request.ProductRequest;
import com.crochet.spring.jpa.demo.payload.result.ProductResult;
import com.crochet.spring.jpa.demo.service.contact.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Transactional
    @Override
    public ProductResult saveProduct(ProductRequest request) {
        Product product;
        if (request.getId() == null) {
            // Create product
            product = Product.builder()
                    .name(request.getName())
                    .price(request.getPrice())
                    .description(request.getDescription())
                    .build();
        } else {
            // Update product
            product = productRepo.findById(UUID.fromString(request.getId()))
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            ProductMapper.INSTANCE.partialUpdate(request, product);
        }
        product = productRepo.save(product);
        return ProductMapper.INSTANCE.productToProductResult(product);
    }

    @Override
    public List<ProductResult> getAll() {
        var products = productRepo.findAll();
        return ProductMapper.INSTANCE.productsToProductResults(products);
    }
}
