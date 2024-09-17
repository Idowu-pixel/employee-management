package com.staff.employees.service;

import com.staff.employees.payload.HeadDTO;
import com.staff.employees.payload.HeadResponse;

public interface HeadService {
    HeadResponse getAllHead(Integer pageNumber, Integer pageSize, String sortBy, String orderBy);
    HeadResponse getHeadById(Long id, Integer pageNumber, Integer pageSize, String sortBy, String orderBy);

    HeadDTO saveHead(HeadDTO headDTO);

    HeadDTO deleteHead(Long id);

    HeadDTO updateHead(Long id, HeadDTO headDTO);
}
