package com.project.ecommerse.service;

import com.project.ecommerse.dto.CategoryResponse;
import com.project.ecommerse.dto.ProductResponse;
import com.project.ecommerse.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAll();
    CategoryResponse getById(long id);
    CategoryResponse getByName(String name);
    CategoryResponse save(Category category);
    List<ProductResponse> getProductsByCategoryId(long categoryId);
    void delete(long id);
    Category findById(long id);

}
