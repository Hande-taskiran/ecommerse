package com.project.ecommerse.service;

import com.project.ecommerse.dto.UserAddressResponse;
import com.project.ecommerse.entity.Address;

public interface AddressService {

    UserAddressResponse getByUserId(long userId);
    UserAddressResponse save(long userId, Address address);

}
