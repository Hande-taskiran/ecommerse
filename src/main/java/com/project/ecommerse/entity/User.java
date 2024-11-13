package com.project.ecommerse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @NotNull
    @Size(min = 10, max = 70)
    @Email(message = "wrong email format ")
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Favourite favourite;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

}
