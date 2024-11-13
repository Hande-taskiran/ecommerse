package com.project.ecommerse.service;

import com.project.ecommerse.dto.ProductResponse;
import com.project.ecommerse.dto.UserResponse;

import java.util.List;

public interface FavouriteService {
    List<ProductResponse> getProductsByUserId(long userId);
    UserResponse getUserByFavouritesId(long favouritesId);
    UserResponse addProductToFavourite(long userId, long productId);
}
