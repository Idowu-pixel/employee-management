package com.staff.employees.controller;

import com.staff.employees.config.AppConstant;
import com.staff.employees.payload.ManagerDTO;
import com.staff.employees.payload.ManagerResponse;
import com.staff.employees.service.ManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ManagerController {

    private ManagerService managerService;

    @GetMapping("/public/managers")
    public ResponseEntity<ManagerResponse> getAllManagers(@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber, @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize, @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_MANAGER_BY, required = false) String sortBy, @RequestParam(value = "orderBy", defaultValue = AppConstant.SORT_DIR, required = false) String orderBy) {
        ManagerResponse managerResponse = managerService.getAllManagers(pageNumber,pageSize,sortBy,orderBy);
        return new ResponseEntity<>(managerResponse, HttpStatus.OK);
    }
    @GetMapping("/public/managers/{id}")
    public ResponseEntity<ManagerDTO> getManagerById(@PathVariable Long id) {
        ManagerDTO managerDTO = managerService.getManagerById(id);
        return new ResponseEntity<>(managerDTO, HttpStatus.OK);
    }

    public ResponseEntity<ManagerDTO> saveManager(@RequestBody ManagerDTO managerDTO) {
        ManagerDTO managerDTO1 = managerService.saveManager(managerDTO);
        return new ResponseEntity<>(managerDTO, HttpStatus.CREATED);
    }

    @PutMapping("/admin/managers/{id}")
    public ResponseEntity<ManagerDTO> updateNManager(@PathVariable Long id, @RequestBody ManagerDTO managerDTO) {
        ManagerDTO managerDTO1 = managerService.updateNManager(id,managerDTO);
        return new ResponseEntity<>(managerDTO, HttpStatus.OK);
    }

    @DeleteMapping("/admin/managers/{id}")
    public ResponseEntity<ManagerDTO> deleteManager(@PathVariable Long id) {
        ManagerDTO managerDTO = managerService.deleteManager(id);
        return new ResponseEntity<>(managerDTO, HttpStatus.OK);
    }


}
