package com.staff.employees.payload;

import com.staff.employees.model.Employee;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long addressId;
    private String street;
    private String city;
    private int zipCode;
    private Employee employee;

}
