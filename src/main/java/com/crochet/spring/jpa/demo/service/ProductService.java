package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.model.Product;
import com.crochet.spring.jpa.demo.payload.dto.ProductDTO;
import com.crochet.spring.jpa.demo.payload.request.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDTO createOrUpdate(ProductRequest request, MultipartFile[] files);

    List<ProductDTO> getAll();

    Product getById(UUID uuid);

    List<Product> getProductsFromCart(Customer customer);
}
