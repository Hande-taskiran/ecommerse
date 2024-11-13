package com.project.ecommerse.controller;

import com.project.ecommerse.dto.ProductResponse;
import com.project.ecommerse.entity.Product;
import com.project.ecommerse.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/products")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class ProductController {
    private ProductService productService;

    @GetMapping("/")
    public List<ProductResponse> getAll(){
        return productService.getAll();
    }


    @GetMapping("/id/{id}")
    public ProductResponse getById(@PathVariable long id){
        return productService.getById(id);
    }

    @GetMapping("/name/{name}")
    public ProductResponse getByName(@PathVariable String name){
        return productService.getByName(name);
    }

    @PostMapping("/")
    public ProductResponse save(@RequestBody Product product){
        return productService.save(product);
    }

    @PostMapping("/saveCategory/{categoryId}")
    public ProductResponse saveProductToCategory(@PathVariable long categoryId, @RequestBody Product product){
        return productService.saveProductToCategory(categoryId, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        productService.delete(id);
    }

}
