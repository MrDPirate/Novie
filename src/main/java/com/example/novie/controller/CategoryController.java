package com.example.novie.controller;

import com.example.novie.model.Category;
import com.example.novie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Create Category
    @PostMapping
    public Category createCategory(@RequestBody Category categoryObject) {
        return categoryService.createCategory(categoryObject);
    }

    // Get all Categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Get Category by ID
    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    // Update Category
    @PutMapping("/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId, @RequestBody Category categoryObject) {
        return categoryService.updateCategory(categoryId, categoryObject);
    }

    // Delete Category
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}

