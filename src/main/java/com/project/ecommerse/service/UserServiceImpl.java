package com.project.ecommerse.service;

import com.project.ecommerse.dto.DtoConverter;
import com.project.ecommerse.dto.OrderResponse;
import com.project.ecommerse.dto.UserResponse;
import com.project.ecommerse.entity.Order;
import com.project.ecommerse.entity.User;
import com.project.ecommerse.exception.EcommerseException;
import com.project.ecommerse.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Override
    public List<UserResponse> getAll() {
        return DtoConverter.convertResponseList(userRepository.findAll());
    }

    @Override
    public UserResponse getById(long id) {
         User user = userRepository.findById(id).orElseThrow(() -> {
            throw new EcommerseException("User with given id not exist: " + id, HttpStatus.NOT_FOUND);
        });
        return DtoConverter.convertResponse(user);
    }

    @Override
    public UserResponse save(User user) {
        User saved = userRepository.save(user);
        return DtoConverter.convertResponse(saved);
    }

    @Override
    public void delete(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EcommerseException("user not found with id: " + id, HttpStatus.NOT_FOUND));

        userRepository.delete(user);
    }

    public List<OrderResponse> getUserOrders(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EcommerseException("User not found with id: " + userId, HttpStatus.NOT_FOUND));
                List<Order> orders = user.getOrders();
        return DtoConverter.convertOrderResponseList(orders);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new EcommerseException("User with given id not exist: " + id, HttpStatus.NOT_FOUND);
        });
    }
}
