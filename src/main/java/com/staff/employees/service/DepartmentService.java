package com.staff.employees.service;

import com.staff.employees.payload.DepartmentDTO;
import com.staff.employees.payload.DepartmentResponse;

public interface DepartmentService {
    DepartmentResponse getAllDepartment(Integer pageNumber, Integer pageSize, String sortBy, String orderBy);

    DepartmentResponse getDepartmentByDepartmentName(String name, Integer pageNumber, Integer pageSize, String sortBy, String orderBy);

    DepartmentDTO addDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO getDepartmentBId(Long id);

    DepartmentDTO deleteDepartment(Long id);

    DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO);
}
