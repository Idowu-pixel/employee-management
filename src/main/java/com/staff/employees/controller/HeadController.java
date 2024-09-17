package com.staff.employees.controller;

import com.staff.employees.config.AppConstant;
import com.staff.employees.payload.HeadDTO;
import com.staff.employees.payload.HeadResponse;
import com.staff.employees.service.HeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HeadController {
    @Autowired
    private HeadService headService;
    @GetMapping("/public/heads")
    public ResponseEntity<HeadResponse> getAllHead(@RequestParam(name = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(name = "pageSize",defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize, @RequestParam(name = "sortBy",defaultValue = AppConstant.SORT_HEAD_BY, required = false)  String sortBy, @RequestParam(name = "orderBy",defaultValue = AppConstant.SORT_DIR, required = false) String orderBy) {
        HeadResponse headResponse = headService.getAllHead(pageNumber,pageSize,sortBy,orderBy);
        return new ResponseEntity<>(headResponse, HttpStatus.OK);
    }

    @GetMapping("/public/heads/{id}")
    public ResponseEntity<HeadResponse> getHeadById(@PathVariable Long id,@RequestParam(name = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(name = "pageSize",defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize, @RequestParam(name = "sortBy",defaultValue = AppConstant.SORT_HEAD_BY, required = false)  String sortBy, @RequestParam(name = "orderBy",defaultValue = AppConstant.SORT_DIR, required = false) String orderBy) {
        HeadResponse headResponse = headService.getHeadById(id,pageNumber,pageSize,sortBy,orderBy);
        return new ResponseEntity<>(headResponse, HttpStatus.OK);
    }
    @PostMapping("/public/heads")
    public ResponseEntity<HeadDTO> saveHead(@RequestBody HeadDTO headDTO) {
        HeadDTO headDTO1 = headService.saveHead(headDTO);
        return new ResponseEntity<>(headDTO1, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/heads/{id}")
    public ResponseEntity<HeadDTO> deleteHead(@PathVariable Long id) {
        HeadDTO headDTO = headService.deleteHead(id);
        return new ResponseEntity<>(headDTO, HttpStatus.OK);
    }

    public ResponseEntity<HeadDTO> updateHead(@PathVariable Long id, @RequestBody HeadDTO headDTO) {
        HeadDTO headDTO1 = headService.updateHead(id,headDTO);
        return new ResponseEntity<>(headDTO1, HttpStatus.OK);
    }

}
