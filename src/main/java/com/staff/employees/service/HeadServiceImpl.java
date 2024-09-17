package com.staff.employees.service;

import com.staff.employees.exception.APIException;
import com.staff.employees.exception.ResourceNotFoundException;
import com.staff.employees.model.Head;
import com.staff.employees.payload.HeadDTO;
import com.staff.employees.payload.HeadResponse;
import com.staff.employees.repository.HeadRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeadServiceImpl implements HeadService{
    @Autowired
    private HeadRepository headRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public HeadResponse getAllHead(Integer pageNumber, Integer pageSize, String sortBy, String orderBy) {
        Sort sortAndOrder = orderBy.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortAndOrder);

        Page<Head> headPage = headRepository.findAll(pageDetails);

        List<Head> headList = headPage.getContent();
        List<HeadDTO> headDTOList = headList.stream()
                .map(head -> modelMapper.map(head, HeadDTO.class))
                .toList();

        HeadResponse headResponse = new HeadResponse();
        headResponse.setContent(headDTOList);
        headResponse.setPageNumber(headPage.getNumber());
        headResponse.setPageSize(headPage.getSize());
        headResponse.setTotalElements((int) headPage.getTotalElements());
        headResponse.setTotalPages(headPage.getTotalPages());
        headResponse.setLastPage(headPage.isLast());
        return headResponse;

    }

    @Override
    public HeadResponse getHeadById(Long id, Integer pageNumber, Integer pageSize, String sortBy, String orderBy) {
        Sort sortByAndOrderBy = orderBy.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrderBy);
        Page<Head> headPage = headRepository.findByHeadId(id,pageDetails);
        List<Head> headList = headPage.getContent();
        List<HeadDTO> headDTOList = headList.stream().map(head -> modelMapper.map(head, HeadDTO.class)).toList();

        HeadResponse headResponse = new HeadResponse();
        headResponse.setContent(headDTOList);
        headResponse.setPageNumber(headPage.getNumber());
        headResponse.setPageSize(headPage.getSize());
        headResponse.setTotalElements((int) headPage.getTotalElements());
        headResponse.setTotalPages(headPage.getTotalPages());
        headResponse.setLastPage(headPage.isLast());
        return headResponse;


    }

    @Override
    public HeadDTO saveHead(HeadDTO headDTO) {
        Head head = modelMapper.map(headDTO, Head.class);
        Head headFromDB = headRepository.findByName(head.getName());
        if(headFromDB != null) {
            throw new APIException("Head already exist");
        }
        headRepository.save(head);
        return modelMapper.map(head, HeadDTO.class);
    }

    @Override
    public HeadDTO deleteHead(Long id) {
        Head headFromDB = headRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found","Head","headId",id));
        headRepository.delete(headFromDB);
        return modelMapper.map(headFromDB, HeadDTO.class);

    }

    @Override
    public HeadDTO updateHead(Long id, HeadDTO headDTO) {
        Head head = modelMapper.map(headDTO, Head.class);
        Head headFromDB = headRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found","Head","headId",id));

        headFromDB.setHeadId(head.getHeadId());
        headFromDB.setEmployee(head.getEmployee());
        headFromDB.setDepartment(head.getDepartment());
        return modelMapper.map(headFromDB, HeadDTO.class);
    }

}
