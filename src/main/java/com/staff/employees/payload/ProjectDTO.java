package com.staff.employees.payload;

import com.staff.employees.model.Employee;
import com.staff.employees.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private Long projectId;
    private String name;
    private double budget;
    private Set<Employee> employee;
    private List<Task> task;
}
