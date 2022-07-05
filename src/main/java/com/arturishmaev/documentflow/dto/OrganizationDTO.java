package com.arturishmaev.documentflow.dto;

import com.arturishmaev.documentflow.entity.DepartmentEntity;
import com.arturishmaev.documentflow.entity.EmployeeEntity;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrganizationDTO {
    private Long id;

    private String name;

    private String physicalAddress;

    private String legalAddress;

    private EmployeeEntity director;

    private Set<DepartmentEntity> departments;
}
