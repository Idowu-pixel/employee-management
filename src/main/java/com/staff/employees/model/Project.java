package com.staff.employees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    private String name;
    private double budget;
    @JsonIgnore
    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employee;
    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private List<Task> task;
}
