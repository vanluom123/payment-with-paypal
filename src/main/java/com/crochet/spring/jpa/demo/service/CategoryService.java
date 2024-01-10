package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.dto.CategoryDTO;
import com.crochet.spring.jpa.demo.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    String createOrUpdate(CategoryDTO request);

    List<Category> getCategories();

    Category getCategoryById(UUID id);
}
