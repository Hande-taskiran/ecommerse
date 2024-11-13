package com.project.ecommerse.controller;

import com.project.ecommerse.dto.UserAddressResponse;
import com.project.ecommerse.entity.Address;
import com.project.ecommerse.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Validated
@RestController
@RequestMapping("/addresses")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class AddressController {
    private AddressService addressService;


    @PostMapping("/save/{userId}")
    public UserAddressResponse save(@PathVariable long userId,
                                    @RequestBody Address address){
        return addressService.save(userId, address);
    }

    @GetMapping("/{userId}")
    public UserAddressResponse getByUserId(@PathVariable long userId){
        return addressService.getByUserId(userId);
    }

}
