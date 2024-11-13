package com.project.ecommerse.service;

import com.project.ecommerse.dto.CategoryResponse;
import com.project.ecommerse.dto.DtoConverter;
import com.project.ecommerse.dto.ProductResponse;
import com.project.ecommerse.entity.Category;
import com.project.ecommerse.exception.EcommerseException;
import com.project.ecommerse.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAll() {
        return DtoConverter.convertCategoryResponseList(categoryRepository.findAll());
    }

    @Override
    public CategoryResponse getById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new EcommerseException("category with given id not exist " + id, HttpStatus.NOT_FOUND));
        return DtoConverter.convertCategoryResponse(category);
    }

    @Override
    public CategoryResponse getByName(String name) {
        Category category = categoryRepository.findByName(name).orElseThrow(()-> new EcommerseException("category with given name not exist " + name, HttpStatus.NOT_FOUND));
        return DtoConverter.convertCategoryResponse(category);
    }

    @Override
    public CategoryResponse save(Category category) {
        Category saved = categoryRepository.save(category);
        return DtoConverter.convertCategoryResponse(saved);
    }

    @Override
    public List<ProductResponse> getProductsByCategoryId(long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EcommerseException("Category with given id not exist: " + categoryId, HttpStatus.NOT_FOUND));

        return DtoConverter.convertProductResponseList(new ArrayList<>(category.getProducts()));

    }

    @Override
    public void delete(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EcommerseException("Category not found with id: " + id, HttpStatus.NOT_FOUND));

        category.getProducts().forEach(product -> product.getCategories().remove(category));

        categoryRepository.delete(category);
    }

    @Override
    public Category findById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            throw new EcommerseException("User with given id not exist: " + id, HttpStatus.NOT_FOUND);
        });
    }
}
