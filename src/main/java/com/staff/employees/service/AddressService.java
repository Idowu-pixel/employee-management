package com.staff.employees.service;

import com.staff.employees.payload.AddressDTO;
import com.staff.employees.payload.AddressResponse;

public interface AddressService {
    AddressResponse getAllAddress();

    AddressDTO saveAddress(AddressDTO addressDTO);

    AddressDTO deleteAddress(Long addressId);

    AddressDTO updateAddress(Long addressId, AddressDTO addressDTO);


    AddressResponse getAddressByStreet(String street);
}
