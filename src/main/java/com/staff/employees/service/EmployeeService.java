package com.staff.employees.service;

import com.staff.employees.model.Employee;
import com.staff.employees.payload.EmployeeDTO;
import com.staff.employees.payload.EmployeeResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {
    EmployeeResponse getAllEmployees(Integer pageNumber, Integer pageSize,String sortBy, String orderBy);

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO deleteEmployee(Long id);

    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);
}
