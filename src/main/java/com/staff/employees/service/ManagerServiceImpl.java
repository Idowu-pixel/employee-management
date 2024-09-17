package com.staff.employees.service;

import com.staff.employees.exception.APIException;
import com.staff.employees.exception.ResourceNotFoundException;
import com.staff.employees.model.Manager;
import com.staff.employees.payload.ManagerDTO;
import com.staff.employees.payload.ManagerResponse;
import com.staff.employees.repository.ManagerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerRepository managerRepository;

    private ModelMapper modelMapper;
    @Override
    public ManagerResponse getAllManagers(Integer pageNumber, Integer pageSize, String sortBy, String orderBy) {
        Sort sortByAndOrderBy = orderBy.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrderBy);
        Page<Manager> managerPage = managerRepository.findAll(pageDetails);

        List<Manager> managerList = managerPage.getContent();
        List<ManagerDTO> managerDTOS = managerList.stream().map(manager -> modelMapper.map(manager, ManagerDTO.class)).toList();

        ManagerResponse managerResponse = new ManagerResponse();
        managerResponse.setContent(managerDTOS);
        managerResponse.setPageNumber(managerPage.getNumber());
        managerResponse.setPageSize(pageDetails.getPageSize());
        managerResponse.setTotalElements((int) managerPage.getTotalElements());
        managerResponse.setTotalPages(managerPage.getTotalPages());
        managerResponse.setLastPage(managerPage.isLast());
         return managerResponse;
    }

    @Override
    public ManagerDTO getManagerById(Long id) {
        Manager managerFromDB = managerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Manager not found","Manager","managerId",id));
        return modelMapper.map(managerFromDB, ManagerDTO.class);
    }

    @Override
    public ManagerDTO saveManager(ManagerDTO managerDTO) {
        Manager manager = modelMapper.map(managerDTO, Manager.class);
        Manager managerFromDB = managerRepository.findById(manager.getManagerId()).orElseThrow(() -> new ResourceNotFoundException("Manager not found","Manager","managerId", manager.getManagerId()));
        if(managerFromDB != null) {
            throw new APIException("Manager already exist...");
        }
        managerRepository.save(manager);
        return modelMapper.map(manager, ManagerDTO.class);
    }

    @Override
    public ManagerDTO updateNManager(Long id, ManagerDTO managerDTO) {
        Manager manager = modelMapper.map(managerDTO, Manager.class);
        Manager managerFromDB = managerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Manager not found","Mannager","managerId",id));

        managerFromDB.setManagerId(manager.getManagerId());
        managerFromDB.setEmployee(manager.getEmployee());
        managerRepository.save(managerFromDB);
        return modelMapper.map(managerFromDB, ManagerDTO.class);
    }

    @Override
    public ManagerDTO deleteManager(Long id) {
        Manager manager = managerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Manager not found","Mannager","managerId",id));
        managerRepository.delete(manager);
        return modelMapper.map(manager, ManagerDTO.class);
    }
}
