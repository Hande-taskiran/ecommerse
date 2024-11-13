package com.project.ecommerse.repository;

import com.project.ecommerse.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    @Query("SELECT f FROM Favourite f WHERE f.user.id = :userId")
    Optional<Favourite> findByUserId(Long userId);

}
