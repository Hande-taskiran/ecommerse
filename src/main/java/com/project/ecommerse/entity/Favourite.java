package com.project.ecommerse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "favourites", schema = "public")
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = {
            CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {
            CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinTable(name = "favourites_products", schema= "public",
            joinColumns = @JoinColumn(name = "favourites_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id"))
    private List<Product> products;
}
