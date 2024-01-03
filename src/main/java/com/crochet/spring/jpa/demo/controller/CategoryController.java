package com.crochet.spring.jpa.demo.controller;

import com.crochet.spring.jpa.demo.payload.request.CreateOrUpdateCategoryRequest;
import com.crochet.spring.jpa.demo.payload.response.GetCategories;
import com.crochet.spring.jpa.demo.service.CategoryService;
import com.google.gson.Gson;
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

    @Autowired
    private Gson gson;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestParam(value = "id", required = false) UUID id,
                                         @RequestParam("name") String name) {
        CreateOrUpdateCategoryRequest request = new CreateOrUpdateCategoryRequest(id, name);
        var result = categoryService.createOrUpdate(request);
        return ResponseEntity.status(201).body(result);
    }

    @GetMapping
    public ResponseEntity<List<GetCategories>> getAll() {
        var result = categoryService.getCategories();
        var getCategories = result.stream().map(c -> {
            GetCategories category = new GetCategories(c.getId(), c.getName());
            return category;
        }).toList();
        return ResponseEntity.ok(getCategories);
    }
}
