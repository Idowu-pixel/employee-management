package com.staff.employees.payload;

import com.staff.employees.model.Department;
import com.staff.employees.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeadDTO {
    private Long headId;
    private String name;
    private Department department;
    private Employee employee;
}
