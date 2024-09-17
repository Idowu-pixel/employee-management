package com.staff.employees.service;

import com.staff.employees.payload.ManagerDTO;
import com.staff.employees.payload.ManagerResponse;

public interface ManagerService {
    ManagerResponse getAllManagers(Integer pageNumber, Integer pageSize, String sortBy, String orderBy);

    ManagerDTO getManagerById(Long id);

    ManagerDTO saveManager(ManagerDTO managerDTO);

    ManagerDTO updateNManager(Long id, ManagerDTO managerDTO);

    ManagerDTO deleteManager(Long id);

}
