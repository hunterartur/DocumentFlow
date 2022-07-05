package com.arturishmaev.documentflow.entity;

import com.arturishmaev.documentflow.statemachine.AssignmentEvents;
import com.arturishmaev.documentflow.statemachine.AssignmentStates;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.statemachine.StateMachine;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assignments")
public class AssignmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "subject")
    private String subject;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "author")
    private EmployeeEntity author;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "executor")
    private EmployeeEntity executor;

    @Column(name = "date_performance")
    private LocalDate datePerformance;

    @Column(name = "control")
    private Boolean isControl;

    @Column(name = "execution")
    private Boolean isExecution;

    @Column(name = "content")
    private String content;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private AssignmentStates state;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "assignment_document",
            joinColumns = @JoinColumn(name = "assignment_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id"))
    private List<DocumentEntity> documents;

    @Transient
    private StateMachine<AssignmentStates, AssignmentEvents> stateMachine;

    public AssignmentEntity(String subject, EmployeeEntity author, EmployeeEntity executor, LocalDate datePerformance, Boolean isControl, Boolean isExecution, String content, AssignmentStates state, List<DocumentEntity> documents) {
        this.subject = subject;
        this.author = author;
        this.executor = executor;
        this.datePerformance = datePerformance;
        this.isControl = isControl;
        this.isExecution = isExecution;
        this.content = content;
        this.state = state;
        this.documents = documents;
    }

    public void fromAssignment(AssignmentEntity assignment) {
        this.setSubject(assignment.getSubject());
        this.setAuthor(assignment.getAuthor());
        this.setExecutor(assignment.getExecutor());
        this.setDatePerformance(assignment.getDatePerformance());
        this.setIsControl(assignment.getIsControl());
        this.setIsExecution(assignment.getIsExecution());
        this.setContent(assignment.getContent());
        this.setState(assignment.getState());
        this.setDocuments(assignment.getDocuments());
    }
}
