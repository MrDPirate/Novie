package com.example.novie.service;

import com.example.novie.exception.InformationNotFoundException;
import com.example.novie.model.Category;
import com.example.novie.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Create Category
    public Category createCategory(Category categoryObject) {
        return categoryRepository.save(categoryObject);
    }

    // Get all Categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Get Category by ID
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new InformationNotFoundException("Category not found with id: " + categoryId)
                );
    }

    // Update Category
    public Category updateCategory(Long categoryId, Category categoryObject) {

        Category existingCategory = getCategoryById(categoryId);
        existingCategory.setName(categoryObject.getName());

        return categoryRepository.save(existingCategory);
    }

    // Delete Category
    public void deleteCategory(Long categoryId) {

        Category category = getCategoryById(categoryId);
        categoryRepository.delete(category);
    }
}
