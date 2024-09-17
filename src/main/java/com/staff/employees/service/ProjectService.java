package com.staff.employees.service;

import com.staff.employees.payload.ProjectDTO;
import com.staff.employees.payload.ProjectResponse;

public interface ProjectService {
    ProjectResponse getAllProject(Integer pageNumber, Integer pageSize, String sortBy, String orderBy);

    ProjectResponse getProjectById(Long id, Integer pageNumber, Integer pageSize, String sortBy, String orderBy);

    ProjectDTO saveProject(ProjectDTO projectDTO);

    ProjectDTO updateProject(Long id, ProjectDTO projectDTO);

    ProjectDTO deleteProject(Long id);

    ProjectResponse getProjectByName(String name, Integer pageNumber, Integer pageSize, String sortBy, String orderBy);
}
