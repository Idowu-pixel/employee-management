package com.staff.employees.controller;

import com.staff.employees.config.AppConstant;
import com.staff.employees.payload.DepartmentDTO;
import com.staff.employees.payload.DepartmentResponse;
import com.staff.employees.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @GetMapping("/public/departments")
    public ResponseEntity<DepartmentResponse> getAllDepartment(@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize, @RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_DEPARTMENT_BY,required = false) String sortBy, @RequestParam(value = "orderBy",defaultValue = AppConstant.SORT_DIR) String orderBy) {
        DepartmentResponse departmentResponse = departmentService.getAllDepartment(pageNumber,pageSize,sortBy,orderBy);
        return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
    }
    @GetMapping("/public/departments/departmentDTOName/{departmentDTOName}")
    public ResponseEntity<DepartmentResponse> getDepartmentByDepartmentName(@PathVariable DepartmentDTO departmentDTOName, @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize, @RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_DEPARTMENT_BY,required = false) String sortBy, @RequestParam(value = "orderBy",defaultValue = AppConstant.SORT_DIR) String orderBy) {
        DepartmentResponse departmentResponse = departmentService.getDepartmentByDepartmentName(departmentDTOName.getName(),pageNumber,pageSize,sortBy,orderBy);
        return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
    }

    @PostMapping("/public/departments")
    public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO departmentDTO1 = departmentService.addDepartment(departmentDTO);
        return new ResponseEntity<>(departmentDTO1, HttpStatus.CREATED);
    }

    @GetMapping("/public/departments/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentBId(@PathVariable Long id) {
        DepartmentDTO departmentDTO = departmentService.getDepartmentBId(id);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }
    @DeleteMapping("/admin/departments/{id}")
    public ResponseEntity<DepartmentDTO> deleteDepartment(@PathVariable Long id) {
        DepartmentDTO departmentDTO = departmentService.deleteDepartment(id);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }

    @PutMapping("/admin/departments/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id,@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO departmentDTO1 = departmentService.updateDepartment(id, departmentDTO);
        return  new ResponseEntity<>(departmentDTO1, HttpStatus.CREATED);
    }
}
