package com.staff.employees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Head {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long headId;
    private String name;
    @JsonIgnore
    @OneToOne(mappedBy = "head")
    //@JoinColumn(name = "department_id")
    private Department department;
    @JsonIgnore
    @OneToOne(mappedBy = "head")
    //@JoinColumn(name = "employee_id")
    private Employee employee;

}
