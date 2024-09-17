package com.staff.employees.service;

import com.staff.employees.payload.TaskDTO;
import com.staff.employees.payload.TaskResponse;

public interface TaskService {
    TaskResponse getAllTask(Integer pageNumber, Integer pageSize, String sortBy, String orderBy);

    TaskResponse getTaskById(Long id, Integer pageNumber, Integer pageSize, String sortBy, String orderBy);

    TaskResponse getTaskByName(String name, Integer pageNumber, Integer pageSize, String sortBy, String orderBy);

    TaskDTO updateTask(Long id, TaskDTO taskDTO);

    TaskDTO saveTask(TaskDTO taskDTO);

    TaskDTO deleteTask(Long id);
}
