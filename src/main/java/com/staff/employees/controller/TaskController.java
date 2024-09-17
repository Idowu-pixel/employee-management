package com.staff.employees.controller;

import com.staff.employees.config.AppConstant;
import com.staff.employees.payload.TaskDTO;
import com.staff.employees.payload.TaskResponse;
import com.staff.employees.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @GetMapping("/public/tasks")
    public ResponseEntity<TaskResponse> getAllTask(@RequestParam(name = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(name = "pageSize",defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize, @RequestParam(name = "sortBy",defaultValue = AppConstant.SORT_TASK_BY, required = false) String sortBy, @RequestParam(name = "orderBy",defaultValue = AppConstant.SORT_DIR, required = false) String orderBy) {
        TaskResponse taskResponse = taskService.getAllTask(pageNumber,pageSize,sortBy,orderBy);
        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }

    @GetMapping("/public/tasks/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id, @RequestParam(name = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(name = "pageSize",defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize, @RequestParam(name = "sortBy",defaultValue = AppConstant.SORT_TASK_BY, required = false) String sortBy, @RequestParam(name = "orderBy",defaultValue = AppConstant.SORT_DIR, required = false) String orderBy) {
        TaskResponse taskResponse = taskService.getTaskById(id,pageNumber,pageSize,sortBy,orderBy);
        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }

    @GetMapping("/public/tasks/name/{name}")
    public ResponseEntity<TaskResponse> getTaskByName(@PathVariable String name, @RequestParam(name = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(name = "pageSize",defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize, @RequestParam(name = "sortBy",defaultValue = AppConstant.SORT_TASK_BY, required = false) String sortBy, @RequestParam(name = "orderBy",defaultValue = AppConstant.SORT_DIR, required = false) String orderBy) {
        TaskResponse taskResponse = taskService.getTaskByName(name,pageNumber,pageSize,sortBy,orderBy);
        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }

    @PutMapping("/admin/tasks/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        TaskDTO taskDTO1 = taskService.updateTask(id, taskDTO);
        return new ResponseEntity<>(taskDTO1, HttpStatus.OK);
    }

    @PostMapping("/admin/tasks")
    public ResponseEntity<TaskDTO> saveTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO taskDTO1 = taskService.saveTask(taskDTO);
        return new ResponseEntity<>(taskDTO1, HttpStatus.CREATED);
    }

    public ResponseEntity<TaskDTO> deleteTask(@PathVariable Long id) {
        TaskDTO taskDTO = taskService.deleteTask(id);
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }
}
