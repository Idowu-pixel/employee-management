package com.staff.employees.controller;

import com.staff.employees.payload.AddressDTO;
import com.staff.employees.payload.AddressResponse;
import com.staff.employees.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @GetMapping("/public/addresses")
    public ResponseEntity<AddressResponse> getAllAddress() {
        AddressResponse addressResponse = addressService.getAllAddress();
      return new ResponseEntity<>(addressResponse, HttpStatus.OK);
    }

    @GetMapping("/public/addresses/name/{name}")
    public ResponseEntity<AddressResponse> getAddressByStreet(@PathVariable AddressDTO addressDTO) {
        AddressResponse addressResponse = addressService.getAddressByStreet(addressDTO.getStreet());
        return new ResponseEntity<>(addressResponse, HttpStatus.OK);
    }
    @PostMapping("/public/addresses")
    public ResponseEntity<AddressDTO> saveAddress(@RequestBody AddressDTO addressDTO) {
        AddressDTO addressDTO1 = addressService.saveAddress(addressDTO);
        return new ResponseEntity<>(addressDTO1, HttpStatus.CREATED);
    }
    @DeleteMapping("/admin/addresses/{addressId}")
    public ResponseEntity<AddressDTO> deleteAddress(@PathVariable Long addressId) {
        AddressDTO addressDTO = addressService.deleteAddress(addressId);
        return new ResponseEntity<>(addressDTO, HttpStatus.OK);
    }
    @PutMapping("/admin/addresses/{addressId}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long addressId, @RequestBody AddressDTO addressDTO) {
        AddressDTO addressDTO1 = addressService.updateAddress(addressId,addressDTO);
        return new ResponseEntity<>(addressDTO1, HttpStatus.OK);
    }
}
