package com.project.ecommerse.controller;

import com.project.ecommerse.dto.ProductResponse;
import com.project.ecommerse.dto.UserResponse;
import com.project.ecommerse.service.FavouriteService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/favourites")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class FavouriteController {
    private final FavouriteService favouriteService;

    @GetMapping("/{userId}/products")
    public List<ProductResponse> getFavouriteProducts(@PathVariable long userId) {
        return favouriteService.getProductsByUserId(userId);
    }

    @GetMapping("/{favouriteId}/user")
    public UserResponse getUserByFavourites(@PathVariable long favouriteId) {
        return favouriteService.getUserByFavouritesId(favouriteId);
    }

    @PostMapping("/{userId}/products/{productId}")
    public UserResponse addProductToFavourite(@PathVariable long userId, @PathVariable long productId) {
        return favouriteService.addProductToFavourite(userId, productId);
    }
}
