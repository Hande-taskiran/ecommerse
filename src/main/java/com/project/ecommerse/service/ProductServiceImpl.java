package com.project.ecommerse.service;

import com.project.ecommerse.dto.DtoConverter;
import com.project.ecommerse.dto.ProductResponse;
import com.project.ecommerse.entity.Category;
import com.project.ecommerse.entity.Product;
import com.project.ecommerse.exception.EcommerseException;
import com.project.ecommerse.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    private CategoryService categoryService;


    @Override
    public List<ProductResponse> getAll() {
        return DtoConverter.convertProductResponseList(productRepository.findAll());
    }

    @Override
    public ProductResponse getById(long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new EcommerseException("product with given id not exist " + id, HttpStatus.NOT_FOUND));
        return DtoConverter.convertProductResponse(product);
    }

    @Override
    public ProductResponse getByName(String name) {
        Product product = productRepository.findByName(name).orElseThrow(()-> new EcommerseException("product with given name not exist " + name, HttpStatus.NOT_FOUND));
        return DtoConverter.convertProductResponse(product);
    }

    @Override
    public ProductResponse save(Product product) {
        Product saved = productRepository.save(product);
        return DtoConverter.convertProductResponse(saved);
    }

    @Override
    public ProductResponse saveProductToCategory(long categoryId, Product product) {
        Category foundCategory = categoryService.findById(categoryId);
        foundCategory.addProduct(product);
        product.addCategory(foundCategory);
        Product savedProduct = productRepository.save(product);
        return DtoConverter.convertProductResponse(savedProduct);

    }

    @Override
    public void delete(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EcommerseException("Product not found with id: " + id, HttpStatus.NOT_FOUND));

        product.getCategories().forEach(category -> category.getProducts().remove(product));

        productRepository.delete(product);
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id).orElseThrow(() -> {
            throw new EcommerseException("User with given id not exist: " + id, HttpStatus.NOT_FOUND);
        });
    }
}
