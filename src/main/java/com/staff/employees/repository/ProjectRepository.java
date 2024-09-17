package com.staff.employees.repository;

import com.staff.employees.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByName(String name);

    Page<Project> findByProjectId(Long id, Pageable pageDetails);

    Page<Project> findByName(String name, Pageable pageDetails);
}
