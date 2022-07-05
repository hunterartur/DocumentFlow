package com.arturishmaev.documentflow.dto;

import com.arturishmaev.documentflow.entity.EmployeeEntity;
import com.arturishmaev.documentflow.entity.OrganizationEntity;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentDTO {
    private Long id;

    private String name;

    private OrganizationEntity organization;

    private EmployeeEntity director;

    private List<EmployeeEntity> employees;
}
