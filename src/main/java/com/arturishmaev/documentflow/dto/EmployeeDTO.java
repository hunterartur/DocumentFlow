package com.arturishmaev.documentflow.dto;

import com.arturishmaev.documentflow.entity.AssignmentEntity;
import com.arturishmaev.documentflow.entity.DepartmentEntity;
import com.arturishmaev.documentflow.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String post;
    private DepartmentEntity department;
    private List<AssignmentEntity> assignmentsAuthor;
    private List<AssignmentEntity> assignmentsExecutor;
    private String email;
    private Set<RoleEntity> roles;
}
