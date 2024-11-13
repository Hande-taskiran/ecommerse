package com.project.ecommerse.controller;

import com.project.ecommerse.dto.CategoryResponse;
import com.project.ecommerse.dto.ProductResponse;
import com.project.ecommerse.entity.Category;
import com.project.ecommerse.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping("/")
    public List<CategoryResponse> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/id/{id}")
    public CategoryResponse getById(@PathVariable long id){
        return categoryService.getById(id);
    }

    @GetMapping("/name/{name}")
    public CategoryResponse getByName(@PathVariable String name){
        return categoryService.getByName(name);
    }

    @PostMapping("/")
    public CategoryResponse save(@RequestBody Category category){
        return categoryService.save(category);
    }

    @GetMapping("/{id}/products")
    public List<ProductResponse> getProductsByCategoryId(@PathVariable long id) {
        return categoryService.getProductsByCategoryId(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        categoryService.delete(id);
    }

}
