package com.project.ecommerse.dto;

import com.project.ecommerse.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DtoConverter {
    public static UserResponse convertResponse(User user){
        return new UserResponse(user.getId(), user.getEmail());
    }

    public static List<UserResponse> convertResponseList(List<User> users){
        List<UserResponse> responseList = new ArrayList<>();
        users.forEach(user -> {
            responseList.add(new UserResponse(user.getId(), user.getEmail()));
        });
        return responseList;
    }

    public static UserAddressResponse convertUserAdress(Address address){
        UserAddressResponse userAddressResponse = new UserAddressResponse(address.getUser().getId(),
                address.getId(), address.getCountry());
        return userAddressResponse;
    }

    public static Optional<UserAddressResponse> convertUserAdressOp(Address address) {
        if (address == null || address.getUser() == null) {
            return Optional.empty();
        }

        UserAddressResponse response = new UserAddressResponse(address.getUser().getId(), address.getId(), address.getCountry());
        return Optional.of(response);
    }

    public static CategoryResponse convertCategoryResponse(Category category){

            return new CategoryResponse(category.getId(), category.getName());

    }

    public static List<CategoryResponse> convertCategoryResponseList(List<Category> categories){
        List<CategoryResponse> responseList = new ArrayList<>();
        categories.forEach(category -> {
            responseList.add(new CategoryResponse(category.getId(), category.getName()));
        });
        return responseList;
    }

    public static List<ProductResponse> convertProductResponseList(List<Product> products){
        List<ProductResponse> responseList = products.stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getPrice()))
                .distinct()
                .collect(Collectors.toList());

        return responseList;
    }

    public static ProductResponse convertProductResponse(Product product){
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }

    public static List<OrderResponse> convertOrderResponseList(List<Order> orders){
        List<OrderResponse> responseList = orders.stream()
                .map(order -> new OrderResponse(order.getId(), order.getOrderPrice()))
                .distinct()
                .collect(Collectors.toList());

        return responseList;
    }

    public static OrderResponse convertOrderResponse(Order order){
        return new OrderResponse(order.getId(), order.getOrderPrice());
    }

}
