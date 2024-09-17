package com.staff.employees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    private String name;
    private String location;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "head_id")
    private Head head;
    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Employee> employee;

}
