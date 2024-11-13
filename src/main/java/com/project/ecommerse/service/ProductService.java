package com.project.ecommerse.service;

import com.project.ecommerse.dto.ProductResponse;
import com.project.ecommerse.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getAll();
    ProductResponse getById(long id);
    ProductResponse getByName(String name);
    ProductResponse save(Product product);
    ProductResponse saveProductToCategory(long categoryId, Product product);
    void delete(long id);
    Product findById(long id);

}
