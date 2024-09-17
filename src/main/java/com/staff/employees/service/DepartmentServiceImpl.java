package com.staff.employees.service;

import com.staff.employees.exception.APIException;
import com.staff.employees.exception.ResourceNotFoundException;
import com.staff.employees.model.Department;
import com.staff.employees.payload.DepartmentDTO;
import com.staff.employees.payload.DepartmentResponse;
import com.staff.employees.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public DepartmentResponse getAllDepartment(Integer pageNumber, Integer pageSize, String sortBy, String orderBy) {
        Sort sortByAndOrderBy = orderBy.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrderBy);
        Page<Department> departmentPage = departmentRepository.findAll(pageDetails);

        List<Department> departmentList = departmentPage.getContent();
        List<DepartmentDTO> departmentDTOS = departmentList.stream().map(department -> modelMapper.map(department, DepartmentDTO.class)).toList();
        if(departmentDTOS.isEmpty()) {
            throw new APIException("No Department is available at this moment ");
        }

        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setContent(departmentDTOS);
        departmentResponse.setPageNumber(departmentPage.getNumber());
        departmentResponse.setPageSize(departmentPage.getSize());
        departmentResponse.setTotalElements((int) departmentPage.getTotalElements());
        departmentResponse.setTotalPages(departmentPage.getTotalPages());
        departmentResponse.setLastPage(departmentPage.isLast());
        return departmentResponse;
    }

    @Override
    public DepartmentResponse getDepartmentByDepartmentName(String name, Integer pageNumber, Integer pageSize, String sortBy, String orderBy) {

        Sort sortByAndOrderBy = orderBy.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrderBy);
        Page<Department> departmentPage = departmentRepository.findByName(name,pageDetails);

        List<Department> departmentList = departmentPage.getContent();
        if(departmentList.isEmpty()) {
            throw new APIException("Department already exist");
        }

        List<DepartmentDTO> departmentDTOS = departmentList.stream().map(department -> modelMapper.map(department,DepartmentDTO.class)).toList();

        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setContent(departmentDTOS);
        departmentResponse.setPageNumber(departmentPage.getNumber());
        departmentResponse.setPageSize(departmentPage.getSize());
        departmentResponse.setTotalElements((int) departmentPage.getTotalElements());
        departmentResponse.setTotalPages(departmentPage.getTotalPages());
        departmentResponse.setLastPage(departmentPage.isLast());
        return departmentResponse;
    }

    @Override
    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) {
        Department department = modelMapper.map(departmentDTO, Department.class);
        Department departmentFromDB = departmentRepository.findByName(department.getName());
        if(departmentFromDB != null) {
            throw new APIException("Department already exist");
        }
        departmentRepository.save(department);
        return modelMapper.map(department, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO getDepartmentBId(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found","Department","departmentId",id));
        return modelMapper.map(department,DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found","Department","departmentId",id));
        departmentRepository.delete(department);
        return modelMapper.map(department, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO) {
        Department department = modelMapper.map(departmentDTO, Department.class);
        Department departmentFromDB = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found","Department","departmentId",id));

        departmentFromDB.setDepartmentId(department.getDepartmentId());
        departmentFromDB.setName(department.getName());
        departmentFromDB.setLocation(department.getLocation());
        departmentFromDB.setHead(department.getHead());
        departmentFromDB.setEmployee(department.getEmployee());
        return modelMapper.map(departmentFromDB, DepartmentDTO.class);
    }
}
