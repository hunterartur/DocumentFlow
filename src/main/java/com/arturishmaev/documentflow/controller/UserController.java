package com.arturishmaev.documentflow.controller;

import com.arturishmaev.documentflow.dto.AssignmentDTO;
import com.arturishmaev.documentflow.dto.Mapper;
import com.arturishmaev.documentflow.entity.*;
import com.arturishmaev.documentflow.service.GeneralService;
import com.arturishmaev.documentflow.service.StateMachineService;
import com.arturishmaev.documentflow.statemachine.AssignmentEvents;
import com.arturishmaev.documentflow.statemachine.AssignmentStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;
import java.util.List;

@Slf4j
@RestController
@PreAuthorize("hasRole('USER')")
@CrossOrigin(origins = "http://localhost:8088")
@RequestMapping(path = "/api/user")
public class UserController {

    private final GeneralService<EmployeeEntity> employeeGeneralService;
    private final GeneralService<DepartmentEntity> departmentGeneralService;
    private final GeneralService<OrganizationEntity> organizationGeneralService;
    private final GeneralService<AssignmentEntity> assignmentGeneralService;
    private final GeneralService<DocumentEntity> documentGeneralService;
    private final StateMachineService<AssignmentStates, AssignmentEvents, AssignmentEntity> stateMachineService;
    private final Mapper mapper;

    public UserController(GeneralService<EmployeeEntity> employeeGeneralService
            , GeneralService<DepartmentEntity> departmentGeneralService
            , GeneralService<OrganizationEntity> organizationGeneralService
            , GeneralService<AssignmentEntity> assignmentGeneralService
            , StateMachineService<AssignmentStates, AssignmentEvents, AssignmentEntity> stateMachineService
            , GeneralService<DocumentEntity> documentGeneralService, Mapper mapper) {

        this.employeeGeneralService = employeeGeneralService;
        this.departmentGeneralService = departmentGeneralService;
        this.organizationGeneralService = organizationGeneralService;
        this.assignmentGeneralService = assignmentGeneralService;
        this.documentGeneralService = documentGeneralService;
        this.stateMachineService = stateMachineService;
        this.mapper = mapper;
    }

    //CRUD Document
    @GetMapping(path = "/document/")
    public ResponseEntity<List<DocumentEntity>> getAllDocument() {
        List<DocumentEntity> documents = documentGeneralService.findAll();
        return !documents.isEmpty() ? new ResponseEntity<>(documents, HttpStatus.OK)
                                    : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/document/{id}")
    public ResponseEntity<DocumentEntity> getDocument(@PathVariable Long id) {
        DocumentEntity document = documentGeneralService.findById(id);
        return (document != null) ? new ResponseEntity<>(document, HttpStatus.OK)
                                  : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/document/")
    public ResponseEntity<DocumentEntity> createDocument(@RequestBody DocumentEntity document) {
        try {
            DocumentEntity documentEntity = documentGeneralService.saveOrUpdate(document);
            return new ResponseEntity<>(documentEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            log.warn("Document creation error");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/document/{id}")
    public ResponseEntity<DocumentEntity> updateDocument(@PathVariable Long id, @RequestBody DocumentEntity document) {
        DocumentEntity documentEntity = documentGeneralService.findById(id);
        if (documentEntity != null) {
            documentEntity.fromDocumentEntity(document);
            documentGeneralService.saveOrUpdate(documentEntity);
            return new ResponseEntity<>(documentEntity, HttpStatus.CREATED);
        } else {
            log.warn("Document update error");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/document/{id}")
    public ResponseEntity<HttpStatus> deleteDocument(@PathVariable Long id) {
        try {
            documentGeneralService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.warn("Document not exists");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //CRUD Assignment
    @GetMapping(path = "/assignment/")
    public ResponseEntity<List<AssignmentEntity>> getAllAssignment() {
        List<AssignmentEntity> assignments = assignmentGeneralService.findAll();
        return assignments.isEmpty() ? new ResponseEntity<>(assignments, HttpStatus.OK)
                                     : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/assignment/{id}")
    public ResponseEntity<AssignmentEntity> getAssignment(@PathVariable Long id) {
        AssignmentEntity assignment = assignmentGeneralService.findById(id);
        return (assignment != null) ? new ResponseEntity<>(assignment, HttpStatus.OK)
                                    : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/assignment/")
    public ResponseEntity<AssignmentEntity> createAssignment(@RequestBody AssignmentEntity assignment) {
        try {
            AssignmentEntity assignmentEntity = assignmentGeneralService.saveOrUpdate(assignment);
            return new ResponseEntity<>(assignmentEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            log.warn("Assignment creation error");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/assignment/{id}")
    public ResponseEntity<AssignmentEntity> updateAssignment(@PathVariable Long id, @RequestBody AssignmentEntity assignment) {
        AssignmentEntity assignmentEntity = assignmentGeneralService.findById(id);
        if (assignmentEntity != null) {
            assignmentEntity.fromAssignment(assignment);
            assignmentGeneralService.saveOrUpdate(assignmentEntity);
            return new ResponseEntity<>(assignmentEntity, HttpStatus.CREATED);
        } else {
            log.warn("Assignment update error");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/assignment/{id}")
    public ResponseEntity<HttpStatus> deleteAssignment(@PathVariable Long id) {
        try {
            assignmentGeneralService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.warn("Assignment not exists");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //с фронта принимаем событие и поручение
    @PostMapping(path = "/assignment/events/{Event}")
    public ResponseEntity<String> sendEvent(@PathVariable String event, @RequestBody AssignmentDTO assignmentDTO) {
        try {
            AssignmentEvents nextEvent = stateMachineService.sendEvent(AssignmentEvents.valueOf(event), mapper.toEntity(assignmentDTO));
            return new ResponseEntity<>(nextEvent.name(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //этот список всех событий нужно отдать фронту
    @GetMapping(path = "/assignment/events/")
    public ResponseEntity<?> allEvent() {
        return new ResponseEntity<>(EnumSet.allOf(AssignmentEvents.class), HttpStatus.OK);
    }
}
