package com.project.ecommerse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "ordr_details")
    @Size(min = 2, max = 100)
    private String ordrDetails;

    @Column(name = "order_price")
    @Min(100)
    private double orderPrice;

    @ManyToOne(cascade = {
            CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {
            CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinTable(name = "orders_products", schema= "public",
    joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
}
