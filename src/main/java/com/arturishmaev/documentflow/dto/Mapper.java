package com.arturishmaev.documentflow.dto;

import com.arturishmaev.documentflow.entity.*;
import com.arturishmaev.documentflow.service.GeneralService;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private final GeneralService<EmployeeEntity> employeeGeneralService;
    private final GeneralService<RoleEntity> roleService;
    private final GeneralService<DepartmentEntity> departmentGeneralService;
    private final GeneralService<OrganizationEntity> organizationGeneralService;
    private final GeneralService<DocumentEntity> documentEntityGeneralService;
    private final GeneralService<AssignmentEntity> assignmentEntityGeneralService;

    public Mapper(GeneralService<EmployeeEntity> employeeGeneralService,
                  GeneralService<RoleEntity> roleService,
                  GeneralService<DepartmentEntity> departmentGeneralService,
                  GeneralService<OrganizationEntity> organizationGeneralService,
                  GeneralService<DocumentEntity> documentEntityGeneralService,
                  GeneralService<AssignmentEntity> assignmentEntityGeneralService) {
        this.employeeGeneralService = employeeGeneralService;
        this.roleService = roleService;
        this.departmentGeneralService = departmentGeneralService;
        this.organizationGeneralService = organizationGeneralService;
        this.documentEntityGeneralService = documentEntityGeneralService;
        this.assignmentEntityGeneralService = assignmentEntityGeneralService;
    }

    public EmployeeDTO toDto(EmployeeEntity employeeEntity) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employeeEntity.getId());
        employeeDTO.setFirstName(employeeEntity.getFirstName());
        employeeDTO.setLastName(employeeEntity.getLastName());
        employeeDTO.setPatronymic(employeeEntity.getPatronymic());
        employeeDTO.setPost(employeeEntity.getPost());
        employeeDTO.setDepartment(employeeEntity.getDepartment());
        employeeDTO.setAssignmentsAuthor(employeeEntity.getAssignmentsAuthor());
        employeeDTO.setAssignmentsExecutor(employeeEntity.getAssignmentsExecutor());
        employeeDTO.setEmail(employeeEntity.getEmail());
        employeeDTO.setRoles(employeeEntity.getRoles());
        return employeeDTO;
    }

    public OrganizationDTO toDto(OrganizationEntity organizationEntity) {
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setId(organizationEntity.getId());
        organizationDTO.setName(organizationEntity.getName());
        organizationDTO.setPhysicalAddress(organizationEntity.getPhysicalAddress());
        organizationDTO.setLegalAddress(organizationEntity.getLegalAddress());
        organizationDTO.setDirector(organizationEntity.getDirector());
        organizationDTO.setDepartments(organizationEntity.getDepartments());
        return organizationDTO;
    }

    public DepartmentDTO toDto(DepartmentEntity departmentEntity) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(departmentEntity.getId());
        departmentDTO.setName(departmentEntity.getName());
        departmentDTO.setOrganization(departmentEntity.getOrganization());
        departmentDTO.setDirector(departmentEntity.getDirector());
        departmentDTO.setEmployees(departmentEntity.getEmployees());
        return departmentDTO;
    }

    public DocumentDTO toDto(DocumentEntity documentEntity) {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(documentEntity.getId());
        documentDTO.setTitle(documentEntity.getTitle());
        documentDTO.setContent(documentEntity.getContent());
        return documentDTO;
    }

    public AssignmentDTO toDto(AssignmentEntity assignmentEntity) {
        AssignmentDTO assignmentDTO = new AssignmentDTO();
        assignmentDTO.setId(assignmentEntity.getId());
        assignmentDTO.setSubject(assignmentEntity.getSubject());
        assignmentDTO.setDatePerformance(assignmentEntity.getDatePerformance());
        assignmentDTO.setAuthor(assignmentEntity.getAuthor());
        assignmentDTO.setExecutor(assignmentEntity.getExecutor());
        assignmentDTO.setIsControl(assignmentEntity.getIsControl());
        assignmentDTO.setIsExecution(assignmentEntity.getIsExecution());
        assignmentDTO.setDocuments(assignmentEntity.getDocuments());
        assignmentDTO.setState(assignmentEntity.getState());
        assignmentDTO.setContent(assignmentEntity.getContent());
        assignmentDTO.setStateMachine(assignmentEntity.getStateMachine());
        return assignmentDTO;
    }

    public RoleDTO toDto(RoleEntity roleEntity) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(roleEntity.getId());
        roleDTO.setName(roleEntity.getName());
        return roleDTO;
    }

    public EmployeeEntity toEntity(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = employeeGeneralService.findById(employeeDTO.getId());
        employeeEntity.setFirstName(employeeDTO.getFirstName());
        employeeEntity.setLastName(employeeDTO.getLastName());
        employeeEntity.setPatronymic(employeeDTO.getPatronymic());
        employeeEntity.setPost(employeeDTO.getPost());
        employeeEntity.setDepartment(employeeDTO.getDepartment());
        employeeEntity.setAssignmentsAuthor(employeeDTO.getAssignmentsAuthor());
        employeeEntity.setAssignmentsExecutor(employeeDTO.getAssignmentsExecutor());
        employeeEntity.setEmail(employeeDTO.getEmail());
        employeeEntity.setRoles(employeeDTO.getRoles());
        return employeeEntity;
    }

    public OrganizationEntity toEntity(OrganizationDTO organizationDTO) {
        OrganizationEntity organizationEntity = organizationGeneralService.findById(organizationDTO.getId());
        organizationEntity.setName(organizationDTO.getName());
        organizationEntity.setPhysicalAddress(organizationDTO.getPhysicalAddress());
        organizationEntity.setLegalAddress(organizationDTO.getLegalAddress());
        organizationEntity.setDirector(organizationDTO.getDirector());
        organizationEntity.setDepartments(organizationDTO.getDepartments());
        return organizationEntity;
    }

    public DepartmentEntity toEntity(DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity = departmentGeneralService.findById(departmentDTO.getId());
        departmentEntity.setId(departmentDTO.getId());
        departmentEntity.setName(departmentDTO.getName());
        departmentEntity.setOrganization(departmentDTO.getOrganization());
        departmentEntity.setDirector(departmentDTO.getDirector());
        departmentEntity.setEmployees(departmentDTO.getEmployees());
        return departmentEntity;
    }

    public DocumentEntity toEntity(DocumentDTO documentDTO) {
        DocumentEntity documentEntity = documentEntityGeneralService.findById(documentDTO.getId());
        documentEntity.setTitle(documentDTO.getTitle());
        documentEntity.setContent(documentDTO.getContent());
        return documentEntity;
    }

    public AssignmentEntity toEntity(AssignmentDTO assignmentDTO) {
        AssignmentEntity assignmentEntity = assignmentEntityGeneralService.findById(assignmentDTO.getId());
        assignmentEntity.setSubject(assignmentDTO.getSubject());
        assignmentEntity.setDatePerformance(assignmentDTO.getDatePerformance());
        assignmentEntity.setAuthor(assignmentDTO.getAuthor());
        assignmentEntity.setExecutor(assignmentDTO.getExecutor());
        assignmentEntity.setIsControl(assignmentDTO.getIsControl());
        assignmentEntity.setIsExecution(assignmentDTO.getIsExecution());
        assignmentEntity.setDocuments(assignmentDTO.getDocuments());
        assignmentEntity.setState(assignmentDTO.getState());
        assignmentEntity.setContent(assignmentDTO.getContent());
        return assignmentEntity;
    }

    public RoleEntity toEntity(RoleDTO roleDTO) {
        RoleEntity roleEntity = roleService.findById(roleDTO.getId());
        roleEntity.setName(roleDTO.getName());
        return roleEntity;
    }
}
