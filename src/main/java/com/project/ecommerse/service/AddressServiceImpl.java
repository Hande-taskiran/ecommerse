package com.project.ecommerse.service;

import com.project.ecommerse.dto.DtoConverter;
import com.project.ecommerse.dto.UserAddressResponse;
import com.project.ecommerse.entity.Address;
import com.project.ecommerse.entity.User;
import com.project.ecommerse.exception.EcommerseException;
import com.project.ecommerse.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService{
    private AddressRepository addressRepository;
    private UserService userService;

    @Override
    public UserAddressResponse getByUserId(long userId) {
        Optional<Address> saved = addressRepository.findByUserId(userId);
        if (saved.isEmpty()) {
            throw new EcommerseException("Address with given userId not exist: " + userId, HttpStatus.NOT_FOUND);
        }
        return DtoConverter.convertUserAdressOp(saved.get())
                .orElseThrow(()-> new EcommerseException("Failed to convert Address", HttpStatus.BAD_REQUEST));
    }

    @Override
    public UserAddressResponse save(long userId, Address address) {
        User foundUser = userService.findById(userId);
        foundUser.setAddress(address);
        address.setUser(foundUser);
        Address savedAddress = addressRepository.save(address);
        return DtoConverter.convertUserAdress(savedAddress);

    }
}
