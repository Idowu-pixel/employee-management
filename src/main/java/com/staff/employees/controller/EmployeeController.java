package com.staff.employees.controller;

import com.staff.employees.config.AppConstant;
import com.staff.employees.model.Employee;
import com.staff.employees.payload.EmployeeDTO;
import com.staff.employees.payload.EmployeeResponse;
import com.staff.employees.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/api/public/employees")
    public ResponseEntity<EmployeeResponse> getAllEmployees(@RequestParam(name = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
                                                            @RequestParam(name = "pageSize", defaultValue = AppConstant.PAGE_SIZE,required = false) Integer pageSize,
                                                            @RequestParam(name = "sortBy", defaultValue = AppConstant.SORT_EMPLOYEE_BY,required = false) String sortBy,
                                                            @RequestParam(name = "orderBy", defaultValue = AppConstant.SORT_DIR,required = false) String orderBy) {

        EmployeeResponse employees = employeeService.getAllEmployees(pageNumber,pageSize,sortBy,orderBy);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/api/public/employees")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO employeeDTO1 = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(employeeDTO1,HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/employees/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable Long id) {
         EmployeeDTO deleteEmployee = employeeService.deleteEmployee(id);
        return new ResponseEntity<>(deleteEmployee, HttpStatus.OK);
    }

    @PutMapping("/api/admin/employees/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO employeeDTO1 = employeeService.updateEmployee(id, employeeDTO);
        return new ResponseEntity<>(employeeDTO1, HttpStatus.OK);
    }

}
