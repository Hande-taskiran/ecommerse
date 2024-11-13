package com.project.ecommerse.service;

import com.project.ecommerse.dto.DtoConverter;
import com.project.ecommerse.dto.ProductResponse;
import com.project.ecommerse.dto.UserResponse;
import com.project.ecommerse.entity.Favourite;
import com.project.ecommerse.entity.Product;
import com.project.ecommerse.entity.User;
import com.project.ecommerse.exception.EcommerseException;
import com.project.ecommerse.repository.FavouriteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FavouriteServiceImpl implements FavouriteService{
    private FavouriteRepository favouriteRepository;
    private UserService userService;
    private final ProductService productService;


    @Override
    public List<ProductResponse> getProductsByUserId(long userId) {
        Favourite favourite = favouriteRepository.findByUserId(userId)
                .orElseThrow(() -> new EcommerseException("Favourite not found for user id: " + userId, HttpStatus.NOT_FOUND));

        return DtoConverter.convertProductResponseList(favourite.getProducts());
    }

    @Override
    public UserResponse getUserByFavouritesId(long favouriteId) {
        Favourite favourite = favouriteRepository.findById(favouriteId)
                .orElseThrow(() -> new EcommerseException("Favourite not found with id: " + favouriteId, HttpStatus.NOT_FOUND));

        return DtoConverter.convertResponse(favourite.getUser());
    }

    @Override
    public UserResponse addProductToFavourite(long userId, long productId) {
        User user = userService.findById(userId);

        Product product = productService.findById(productId);

        Favourite favourite = favouriteRepository.findByUserId(userId)
                .orElseThrow(() -> new EcommerseException("favourite not found with userId: " + userId, HttpStatus.NOT_FOUND));

        favourite.getProducts().add(product);
        favouriteRepository.save(favourite);
        return DtoConverter.convertResponse(user);
    }

}
