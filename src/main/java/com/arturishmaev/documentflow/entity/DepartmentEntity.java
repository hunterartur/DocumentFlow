package com.arturishmaev.documentflow.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "departments")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "The field should not be empty!")
    @Column(name = "name")
    private String name;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    private OrganizationEntity organization;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "director")
    private EmployeeEntity director;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<EmployeeEntity> employees;

    public DepartmentEntity(String name, OrganizationEntity organization
            , EmployeeEntity director, List<EmployeeEntity> employees) {
        this.name = name;
        this.organization = organization;
        this.director = director;
        this.employees = employees;
    }

    public void fromDepartment(DepartmentEntity department) {
        this.setName(department.getName());
        this.setOrganization(department.getOrganization());
        this.setDirector(department.getDirector());
        this.setEmployees(department.getEmployees());
    }
}
