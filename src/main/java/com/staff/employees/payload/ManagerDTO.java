package com.staff.employees.payload;

import com.staff.employees.model.Employee;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDTO {
    private Long managerId;
    private Employee employee;
}
