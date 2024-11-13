package com.project.ecommerse.service;

import com.project.ecommerse.dto.OrderResponse;
import com.project.ecommerse.dto.ProductResponse;
import com.project.ecommerse.dto.UserResponse;

import java.util.List;

public interface OrderService {
    List<ProductResponse> getOrderProducts(long orderId);
    UserResponse getUserByOrderId(long orderId);
    OrderResponse addProductToOrder(long orderId, long productId);
}

