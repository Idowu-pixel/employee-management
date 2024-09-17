package com.staff.employees.controller;

import com.staff.employees.config.AppConstant;
import com.staff.employees.payload.ProjectDTO;
import com.staff.employees.payload.ProjectResponse;
import com.staff.employees.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/public/projects")
    public ResponseEntity<ProjectResponse> getAllProject(@RequestParam(name = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(name = "pageSize",defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,@RequestParam(name = "sortBy",defaultValue = AppConstant.SORT_PROJECT_BY, required = false) String sortBy, @RequestParam(name = "orderBy",defaultValue = AppConstant.SORT_DIR, required = false) String orderBy) {
        ProjectResponse projectResponse = projectService.getAllProject(pageNumber,pageSize,sortBy,orderBy);
        return new ResponseEntity<>(projectResponse, HttpStatus.OK);
    }

    @GetMapping("/public/projects/{id}")
    private ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id, @RequestParam(name = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(name = "pageSize",defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize, @RequestParam(name = "sortBy",defaultValue = AppConstant.SORT_PROJECT_BY, required = false) String sortBy, @RequestParam(name = "orderBy",defaultValue = AppConstant.SORT_DIR, required = false) String orderBy) {
        ProjectResponse projectResponse = projectService.getProjectById(id,pageNumber,pageSize,sortBy,orderBy);
        return new ResponseEntity<>(projectResponse,HttpStatus.OK);
    }
    @GetMapping("/public/projects/name/{name}")
    public ResponseEntity<ProjectResponse> getProjectByName(@PathVariable String name, @RequestParam(name = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(name = "pageSize",defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize, @RequestParam(name = "sortBy",defaultValue = AppConstant.SORT_PROJECT_BY, required = false) String sortBy, @RequestParam(name = "orderBy",defaultValue = AppConstant.SORT_DIR, required = false) String orderBy) {
        ProjectResponse projectResponse = projectService.getProjectByName(name,pageNumber,pageSize,sortBy,orderBy);
        return new ResponseEntity<>(projectResponse, HttpStatus.OK);
    }

    @PostMapping("/admin/projects")
    public ResponseEntity<ProjectDTO> saveProject(@RequestBody ProjectDTO projectDTO){
        ProjectDTO projectDTO1 = projectService.saveProject(projectDTO);
        return new ResponseEntity<>(projectDTO1,HttpStatus.CREATED);
    }

    @PutMapping("/admin/projects/{id}")
    private ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        ProjectDTO projectDTO1 = projectService.updateProject(id,projectDTO);
        return new ResponseEntity<>(projectDTO1, HttpStatus.OK);
    }
    @DeleteMapping("/admin/projects/{id}")
    public ResponseEntity<ProjectDTO> deleteProject(@PathVariable Long id){
        ProjectDTO projectDTO = projectService.deleteProject(id);
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }
}
