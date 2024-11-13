package com.project.ecommerse.repository;

import com.project.ecommerse.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a WHERE a.user.id = :userId")
    Optional<Address> findByUserId(long userId);
}
