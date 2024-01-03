package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.model.Category;
import com.crochet.spring.jpa.demo.payload.request.CreateOrUpdateCategoryRequest;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    String createOrUpdate(CreateOrUpdateCategoryRequest request);

    List<Category> getCategories();

    Category getCategoryById(UUID id);
}
