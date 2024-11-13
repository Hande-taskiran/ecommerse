package com.project.ecommerse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "addresses", schema = "public")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 2, max = 50)
    private String street;

    @NotNull
    @Size(min = 2, max = 50)
    private String city;

    @NotNull
    @Size(min = 2, max = 50)
    private String country;

    @NotNull
    @Size(min = 2, max = 100)
    private String description;

    private int no;

    @OneToOne(cascade = {
            CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "user_id")
    private User user;
}
