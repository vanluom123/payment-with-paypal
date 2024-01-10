package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.dto.ProductCreationDTO;
import com.crochet.spring.jpa.demo.dto.ProductDTO;
import com.crochet.spring.jpa.demo.mapper.ProductMapper;
import com.crochet.spring.jpa.demo.model.Cart;
import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.model.Product;
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
    private ShopService shopService;

    @Autowired
    private ProductMapper productMapper;

    @Transactional
    @Override
    public ProductDTO createOrUpdate(ProductCreationDTO request, MultipartFile[] files) {
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
        return productMapper.toDTO(product);
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
    public List<ProductDTO> getAll() {
        var products = productRepo.findAll();
        return productMapper.toDTOs(products);
    }

    @Override
    public Product getById(UUID uuid) {
        return productRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> getProductsFromCart(Customer customer) {
        return customer.getCarts()
                .stream()
                .map(Cart::getProduct)
                .toList();
    }
}
