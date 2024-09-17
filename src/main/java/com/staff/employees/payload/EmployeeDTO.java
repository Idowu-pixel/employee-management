package com.staff.employees.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.staff.employees.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int salary;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateEmployed;
    private Manager manager;
    private Department department;
    private Project project;
    private Address address;
    private Head head;

}
