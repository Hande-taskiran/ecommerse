package com.project.ecommerse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products", schema = "public")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @Min(1)
    private double price;

    @Min(1)
    @Max(5)
    private int rates;

    @Min(value = 1, message = "if stock is less than 1, product is disable")
    private long stock;



    @ManyToMany(cascade = {
            CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinTable(name = "orders_products", schema= "public",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;



    @ManyToMany(cascade = {
            CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinTable(name = "favourites_products", schema= "public",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "favourites_id"))
    private List<Favourite> favourites;



    @ManyToMany(cascade = {
            CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinTable(name = "categories_products", schema= "public",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private List<Category> categories = new ArrayList<>();

    public void addCategory(Category category) {
        this.categories.add(category);
        category.getProducts().add(this);
    }
}
