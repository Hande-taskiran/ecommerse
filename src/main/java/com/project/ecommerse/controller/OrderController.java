package com.project.ecommerse.controller;

import com.project.ecommerse.dto.OrderResponse;
import com.project.ecommerse.dto.ProductResponse;
import com.project.ecommerse.dto.UserResponse;
import com.project.ecommerse.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class OrderController {
    private OrderService orderService;

    @GetMapping("/{orderId}/products")
    public List<ProductResponse> getOrderProducts(@PathVariable long orderId) {
        return orderService.getOrderProducts(orderId);
    }

    @GetMapping("/{orderId}/user")
    public UserResponse getUserByOrderId(@PathVariable long orderId) {
        return orderService.getUserByOrderId(orderId);
    }

    @PostMapping("/{orderId}/products/{productId}")
    public OrderResponse addProductToOrder(@PathVariable long orderId, @PathVariable long productId) {
        return orderService.addProductToOrder(orderId, productId);
    }

}
