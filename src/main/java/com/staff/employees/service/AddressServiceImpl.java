package com.staff.employees.service;

import com.staff.employees.exception.APIException;
import com.staff.employees.exception.ResourceNotFoundException;
import com.staff.employees.model.Address;
import com.staff.employees.payload.AddressDTO;
import com.staff.employees.payload.AddressResponse;
import com.staff.employees.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public AddressResponse getAllAddress() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressDTO> addressDTOS = addresses.stream().map(address -> modelMapper.map(address, AddressDTO.class)).toList();

        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setContent(addressDTOS);
        return addressResponse;
    }

    @Override
    public AddressDTO saveAddress(AddressDTO addressDTO) {
        Address address = modelMapper.map(addressDTO, Address.class);
        Address addressFromDB = addressRepository.findByStreet(address.getStreet());
        if (addressFromDB != null ) {
            throw new APIException("Address Already exist");
        }
        Address address1 = addressRepository.save(address);
        return modelMapper.map(address1, AddressDTO.class);
    }

    @Override
    public AddressDTO deleteAddress(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address not found", "Address","addressId",addressId));
        addressRepository.delete(address);
        return modelMapper.map(address, AddressDTO.class);
    }

    @Override
    public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO) {
        Address address = modelMapper.map(addressDTO, Address.class);
        Address addressInDB = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address not found","Address","addressId",addressId));

        addressInDB.setCity(address.getCity());
        addressInDB.setStreet(address.getStreet());
        addressInDB.setEmployee(address.getEmployee());
        addressInDB.setZipCode(address.getZipCode());
        addressRepository.save(addressInDB);
        return modelMapper.map(addressInDB, AddressDTO.class);
    }

    @Override
    public AddressResponse getAddressByStreet(String street) {
        Address address = addressRepository.findByStreet(street);
        if (address != null) {
            throw new APIException("Address already exist");
        }
        AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);

        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setContent((List<AddressDTO>) addressDTO);
        return addressResponse;
    }


}
