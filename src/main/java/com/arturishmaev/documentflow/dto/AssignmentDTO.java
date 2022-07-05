package com.arturishmaev.documentflow.dto;

import com.arturishmaev.documentflow.entity.DocumentEntity;
import com.arturishmaev.documentflow.entity.EmployeeEntity;
import com.arturishmaev.documentflow.statemachine.AssignmentEvents;
import com.arturishmaev.documentflow.statemachine.AssignmentStates;
import lombok.*;
import org.springframework.statemachine.StateMachine;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AssignmentDTO {

    private Long id;

    private String subject;

    private EmployeeEntity author;

    private EmployeeEntity executor;

    private LocalDate datePerformance;

    private Boolean isControl;

    private Boolean isExecution;

    private String content;

    private AssignmentStates state;

    private StateMachine<AssignmentStates, AssignmentEvents> stateMachine;

    private List<DocumentEntity> documents;
}
