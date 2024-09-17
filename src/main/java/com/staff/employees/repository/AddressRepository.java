package com.staff.employees.repository;

import com.staff.employees.model.Address;
import com.staff.employees.payload.AddressDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByStreet(String street);
}
