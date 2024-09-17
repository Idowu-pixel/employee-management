package com.staff.employees.service;

import com.staff.employees.exception.APIException;
import com.staff.employees.model.Address;
import com.staff.employees.model.Employee;
import com.staff.employees.payload.EmployeeDTO;
import com.staff.employees.payload.EmployeeResponse;
import com.staff.employees.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmployeeRepository employeeRepository;



    @Override
    public EmployeeResponse getAllEmployees(Integer pageNumber,Integer pageSize, String sortBy, String orderBy) {
        Sort sortByAndOrder = orderBy.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Employee> employeePage = employeeRepository.findAll(pageDetails);

       List<Employee> employeeList = employeePage.getContent();
       if(employeeList.isEmpty()) {
           throw new APIException("Employee list not found");
       }

       List<EmployeeDTO> employeeDTOList = employeeList.stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).toList();

       EmployeeResponse employeeResponse = new EmployeeResponse();
       employeeResponse.setContent(employeeDTOList);
       employeeResponse.setPageNumber(employeePage.getNumber());
       employeeResponse.setPageSize(employeePage.getSize());
       employeeResponse.setTotalElements(employeePage.getNumberOfElements());
       employeeResponse.setTotalPages(employeePage.getTotalPages());
       employeeResponse.setLastPage(employeePage.isLast());

       return employeeResponse;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);;
        Employee employeeFromDB = employeeRepository.findByFirstName(employee.getFirstName());
        if(employeeFromDB != null) {
            throw new APIException("Employee with name " + employee + "already exist" );
        }

         employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO deleteEmployee(Long id) {
        Employee  deletedEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        employeeRepository.delete(deletedEmployee);
        return modelMapper.map(deletedEmployee,EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployee  = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));

        savedEmployee.setAddress(employee.getAddress());
        savedEmployee.setEmail(employee.getEmail());
        savedEmployee.setHead(employee.getHead());
        savedEmployee.setDepartment(employee.getDepartment());
        savedEmployee.setManager(employee.getManager());
        savedEmployee.setFirstName(employee.getFirstName());
        savedEmployee.setLastName(employee.getLastName());
        savedEmployee.setDateEmployed(employee.getDateEmployed());
        savedEmployee.setProjects(employee.getProjects());
        savedEmployee.setSalary(employee.getSalary());
         employeeRepository.save(savedEmployee);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }


}
