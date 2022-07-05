package com.arturishmaev.documentflow.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "organization")
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "The field should not be empty!")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "The field should not be empty!")
    @Column(name = "physical_address")
    private String physicalAddress;

    @NotBlank(message = "The field should not be empty!")
    @Column(name = "legal_address")
    private String legalAddress;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "director")
    private EmployeeEntity director;

    @JsonManagedReference
    @OneToMany(mappedBy = "organization")
    private Set<DepartmentEntity> departments;

    public OrganizationEntity(String name, String physicalAddress, String legalAddress, EmployeeEntity director, Set<DepartmentEntity> departments) {
        this.name = name;
        this.physicalAddress = physicalAddress;
        this.legalAddress = legalAddress;
        this.director = director;
        this.departments = departments;
    }

     public void addDepartment(DepartmentEntity department) {
        if (departments.isEmpty()) {
            departments = new HashSet<>();
        }
        departments.add(department);
        department.setOrganization(this);
     }

    public void fromOrganization(OrganizationEntity organization) {
        this.setName(organization.getName());
        this.setPhysicalAddress(organization.getPhysicalAddress());
        this.setLegalAddress(organization.getLegalAddress());
        this.setDirector(organization.getDirector());
        this.setDepartments(organization.getDepartments());
    }
}
