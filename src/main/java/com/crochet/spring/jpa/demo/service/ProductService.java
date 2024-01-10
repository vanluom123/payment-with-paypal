package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.dto.ProductCreationDTO;
import com.crochet.spring.jpa.demo.dto.ProductDTO;
import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDTO createOrUpdate(ProductCreationDTO request, MultipartFile[] files);

    List<ProductDTO> getAll();

    Product getById(UUID uuid);

    List<Product> getProductsFromCart(Customer customer);
}
