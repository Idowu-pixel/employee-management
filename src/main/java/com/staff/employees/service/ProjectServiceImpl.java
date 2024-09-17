package com.staff.employees.service;

import com.staff.employees.exception.APIException;
import com.staff.employees.exception.ResourceNotFoundException;
import com.staff.employees.model.Project;
import com.staff.employees.payload.ProjectDTO;
import com.staff.employees.payload.ProjectResponse;
import com.staff.employees.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ProjectResponse getAllProject(Integer pageNumber, Integer pageSize, String sortBy, String orderBy) {
        Sort sortByAndOrderBy = orderBy.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrderBy);
        Page<Project> projectPage = projectRepository.findAll(pageDetails);

        List<Project> projectList = projectPage.getContent();
        List<ProjectDTO> projectDTOList = projectList.stream().map(project -> modelMapper.map(project, ProjectDTO.class)).toList();

        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setContent(projectDTOList);
        projectResponse.setPageNumber(projectPage.getNumber());
        projectResponse.setPageSize(projectPage.getSize());
        projectResponse.setTotalElements((int) projectPage.getTotalElements());
        projectResponse.setTotalPages(projectPage.getTotalPages());
        projectResponse.setLastPage(projectPage.isLast());
        return projectResponse;
    }

    @Override
    public ProjectResponse getProjectById(Long id, Integer pageNumber, Integer pageSize, String sortBy, String orderBy) {
        Sort sortByAndOrderBy = orderBy.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrderBy);
        Page<Project> projectPage = projectRepository.findByProjectId(id,pageDetails);

        List<Project> projectList = projectPage.getContent();
        List<ProjectDTO> projectDTOList = projectList.stream().map(project -> modelMapper.map(project, ProjectDTO.class)).toList();
        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setContent(projectDTOList);
        return projectResponse;
    }

    @Override
    public ProjectDTO saveProject(ProjectDTO projectDTO) {
        Project project = modelMapper.map(projectDTO, Project.class);
        Project projectFromDB = projectRepository.findByName(project.getName());
        if(projectFromDB != null) {
            throw new APIException("Project already exist in DB");
        }

        projectRepository.save(project);
        return modelMapper.map(project, ProjectDTO.class);
    }

    @Override
    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        Project project = modelMapper.map(projectDTO,Project.class);
        Project projectFromDB = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project Not Found","Project","projecId",id));

        projectFromDB.setProjectId(project.getProjectId());
        projectFromDB.setName(project.getName());
        projectFromDB.setEmployee(project.getEmployee());
        projectFromDB.setBudget(project.getBudget());
        projectFromDB.setTask(project.getTask());
        return modelMapper.map(projectFromDB, ProjectDTO.class);
    }

    @Override
    public ProjectDTO deleteProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project Not Found","Project","projecId",id));
        projectRepository.delete(project);
        return modelMapper.map(project, ProjectDTO.class);
    }

    @Override
    public ProjectResponse getProjectByName(String name, Integer pageNumber, Integer pageSize, String sortBy, String orderBy) {
        Sort sortByAndOrderBy = orderBy.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrderBy);
        Page<Project> projectPage = projectRepository.findByName(name,pageDetails);

        List<Project> projectList = projectPage.getContent();
        List<ProjectDTO> projectDTOList = projectList.stream().map(project -> modelMapper.map(project, ProjectDTO.class)).toList();

        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setContent(projectDTOList);
        projectResponse.setPageNumber(projectPage.getNumber());
        projectResponse.setPageSize(projectPage.getSize());
        projectResponse.setTotalElements((int) projectPage.getTotalElements());
        projectResponse.setTotalPages(projectPage.getTotalPages());
        projectResponse.setLastPage(projectPage.isLast());
        return projectResponse;
    }
}
