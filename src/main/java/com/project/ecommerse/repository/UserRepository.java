package com.project.ecommerse.repository;

import com.project.ecommerse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
