package com.staff.employees.repository;

import com.staff.employees.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByTaskId(Long id, Pageable pageDetails);

    Page<Task> findByTaskName(String name, Pageable pageDetails);

    Task findByTaskName(String name);
}
