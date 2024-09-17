package com.staff.employees.payload;

import com.staff.employees.model.Project;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long taskId;
    private String taskName;
    private String description;
    private boolean status;
    private Project project;
}
