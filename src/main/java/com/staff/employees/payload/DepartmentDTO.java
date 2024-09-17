package com.staff.employees.payload;

import com.staff.employees.model.Employee;
import com.staff.employees.model.Head;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private Long departmentId;
    private String name;
    private String location;
    private Head head;
    private List<Employee> employee;
}
