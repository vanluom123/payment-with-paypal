package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.dto.CategoryDTO;
import com.crochet.spring.jpa.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestParam(value = "id", required = false) UUID id,
                                         @RequestParam("name") String name) {
        CategoryDTO request = new CategoryDTO(id, name);
        var result = categoryService.createOrUpdate(request);
        return ResponseEntity.status(201).body(result);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        var result = categoryService.getCategories();
        var getCategories = result.stream().map(c -> {
            CategoryDTO category = new CategoryDTO(c.getId(), c.getName());
            return category;
        }).toList();
        return ResponseEntity.ok(getCategories);
    }
}
