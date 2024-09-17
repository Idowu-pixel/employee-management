package com.staff.employees.repository;

import com.staff.employees.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Page<Department> findByName(String name, Pageable pageable);

    Department findByName(String name);
}
