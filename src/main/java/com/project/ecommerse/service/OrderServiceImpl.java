package com.project.ecommerse.service;

import com.project.ecommerse.dto.DtoConverter;
import com.project.ecommerse.dto.OrderResponse;
import com.project.ecommerse.dto.ProductResponse;
import com.project.ecommerse.dto.UserResponse;
import com.project.ecommerse.entity.Order;
import com.project.ecommerse.entity.Product;
import com.project.ecommerse.exception.EcommerseException;
import com.project.ecommerse.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    private ProductService productService;

    @Override
    public List<ProductResponse> getOrderProducts(long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EcommerseException("Order not found for id: " + orderId, HttpStatus.NOT_FOUND));
        return DtoConverter.convertProductResponseList(order.getProducts());
    }

    @Override
    public UserResponse getUserByOrderId(long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EcommerseException("Order not found with id: " + orderId, HttpStatus.NOT_FOUND));
        return DtoConverter.convertResponse(order.getUser());
    }

    @Override
    public OrderResponse addProductToOrder(long orderId, long productId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EcommerseException("Order not found with id: " + orderId, HttpStatus.NOT_FOUND));

        Product product = productService.findById(productId);

        order.getProducts().add(product);
        orderRepository.save(order);
        return DtoConverter.convertOrderResponse(orderRepository.save(order));
    }
}
