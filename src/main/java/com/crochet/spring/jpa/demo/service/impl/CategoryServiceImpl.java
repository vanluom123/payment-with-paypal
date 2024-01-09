package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.model.Category;
import com.crochet.spring.jpa.demo.payload.dto.CategoryDTO;
import com.crochet.spring.jpa.demo.repository.CategoryRepo;
import com.crochet.spring.jpa.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public String createOrUpdate(CategoryDTO request) {
        Category category;
        if (request.getId() == null) {
            category = new Category();
        } else {
            category = getCategoryById(request.getId());
        }
        category.setName(request.getName());
        categoryRepo.save(category);
        return "Create category success";
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
