package com.staff.employees.repository;

import com.staff.employees.model.Head;
import com.staff.employees.payload.HeadDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeadRepository extends JpaRepository<Head, Long> {
    Page<Head> findByHeadId(Long id, Pageable pageDetails);

    Head findByName(String name);
}
