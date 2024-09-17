package com.staff.employees.service;

import com.staff.employees.exception.APIException;
import com.staff.employees.exception.ResourceNotFoundException;
import com.staff.employees.model.Task;
import com.staff.employees.payload.TaskDTO;
import com.staff.employees.payload.TaskResponse;
import com.staff.employees.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public TaskResponse getAllTask(Integer pageNumber, Integer pageSize, String sortBy, String orderBy) {
        Sort sortByAndOrderBy = orderBy.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrderBy);
        Page<Task> taskPage = taskRepository.findAll(pageDetails);
        List<Task> taskList = taskPage.getContent();
        List<TaskDTO> taskDTOSList = taskList.stream().map(task -> modelMapper.map(task, TaskDTO.class)).toList();

        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setContent(taskDTOSList);
        taskResponse.setPageNumber(taskPage.getNumber());
        taskResponse.setPageSize(taskPage.getSize());
        taskResponse.setTotalElements((int) taskPage.getTotalElements());
        taskResponse.setTotalPages(taskPage.getTotalPages());
        taskResponse.setLastPage(taskPage.isLast());
        return taskResponse;
    }

    @Override
    public TaskResponse getTaskById(Long id, Integer pageNumber, Integer pageSize, String sortBy, String orderBy) {
        Sort sortByAndOrderBy = orderBy.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrderBy);
        Page<Task> taskPage = taskRepository.findByTaskId(id,pageDetails);

        List<Task> taskList = taskPage.getContent();
        List<TaskDTO> taskDTOList = taskList.stream().map(task -> modelMapper.map(task, TaskDTO.class)).toList();


        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setContent(taskDTOList);
        taskResponse.setPageNumber(taskPage.getNumber());
        taskResponse.setPageSize(taskPage.getSize());
        taskResponse.setTotalElements((int) taskPage.getTotalElements());
        taskResponse.setTotalPages(taskPage.getTotalPages());
        taskResponse.setLastPage(taskPage.isLast());
        return taskResponse;
    }

    @Override
    public TaskResponse getTaskByName(String name, Integer pageNumber, Integer pageSize, String sortBy, String orderBy) {
        Sort sortByAndOrderBy = orderBy.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrderBy);
        Page<Task> taskPage = taskRepository.findByTaskName(name,pageDetails);

        List<Task> taskList = taskPage.getContent();
        List<TaskDTO> taskDTOList = taskList.stream().map(task -> modelMapper.map(task, TaskDTO.class)).toList();

        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setContent(taskDTOList);
        taskResponse.setPageNumber(taskPage.getNumber());
        taskResponse.setPageSize(taskPage.getSize());
        taskResponse.setTotalElements((int) taskPage.getTotalElements());
        taskResponse.setTotalPages(taskPage.getTotalPages());
        taskResponse.setLastPage(taskPage.isLast());
        return taskResponse;
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        Task taskFromDB = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task Not Found","Task","taskId",id));

        taskFromDB.setTaskId(task.getTaskId());
        taskFromDB.setTaskName(task.getTaskName());
        taskFromDB.setDescription(task.getDescription());
        taskFromDB.setProject(task.getProject());
        taskFromDB.setStatus(task.isStatus());
        return modelMapper.map(taskFromDB, TaskDTO.class);
    }

    @Override
    public TaskDTO saveTask(TaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO,Task.class);
        Task taskFromDB = taskRepository.findByTaskName(task.getTaskName());
        if(taskFromDB != null) {
            throw new APIException("Task already exist");
        }

        taskRepository.save(task);
        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public TaskDTO deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task Not Found","Task","taskId",id));
        taskRepository.delete(task);
        return modelMapper.map(task, TaskDTO.class);
    }
}
