package com.project.ecommerse.service;

import com.project.ecommerse.dto.OrderResponse;
import com.project.ecommerse.dto.UserResponse;
import com.project.ecommerse.entity.User;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();
    UserResponse getById(long id);
    UserResponse save(User user);
    void delete(long id);
    List<OrderResponse> getUserOrders(long userId);
    User findById(long id);
}
