package com.project.ecommerse.controller;


import com.project.ecommerse.dto.OrderResponse;
import com.project.ecommerse.dto.UserResponse;
import com.project.ecommerse.entity.User;
import com.project.ecommerse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class UserController {

    private UserService userService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getById(@PathVariable long id){
        return userService.getById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse saveUser(@RequestBody User user){
       return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        userService.delete(id);
    }

    @GetMapping("/{userId}/orders")
    public List<OrderResponse> getUserOrders(@PathVariable long userId) {
        return userService.getUserOrders(userId);
    }

}
