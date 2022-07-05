package com.arturishmaev.documentflow.service;

import com.arturishmaev.documentflow.entity.AssignmentEntity;
import com.arturishmaev.documentflow.exception.AssignmentNotFoundException;
import com.arturishmaev.documentflow.repository.AssignmentRepository;
import com.arturishmaev.documentflow.statemachine.AssignmentEvents;
import com.arturishmaev.documentflow.statemachine.AssignmentStates;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentGeneralServiceImpl implements GeneralService<AssignmentEntity>
        , StateMachineService<AssignmentStates, AssignmentEvents, AssignmentEntity> {

    private static final String ASSIGNMENT_NOT_FOUND_BY_ID_MSG = "Assignment with id = %d not found!";
    private static final String ASSIGNMENT_NOT_FOUND_BY_STATE_MSG = "Assignment with state = %s not found!";
    private static final String ASSIGNMENT_NOT_FOUND_BY_SUBJECT_MSG = "Assignment with subject = %s not found!";

    private final StateMachineFactory<AssignmentStates, AssignmentEvents> stateMachineFactory;
    private final AssignmentRepository repository;

    public AssignmentGeneralServiceImpl(AssignmentRepository repository
            , StateMachineFactory<AssignmentStates, AssignmentEvents> stateMachineFactory) {
        this.repository = repository;
        this.stateMachineFactory = stateMachineFactory;
    }

    @Override
    public List<AssignmentEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public AssignmentEntity findByName(String name) {
        return repository.findBySubject(name)
                .orElseThrow(() -> new AssignmentNotFoundException(String.format(ASSIGNMENT_NOT_FOUND_BY_SUBJECT_MSG, name)));
    }

    @Override
    public AssignmentEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException(String.format(ASSIGNMENT_NOT_FOUND_BY_ID_MSG, id)));
    }

    @Override
    public AssignmentEntity saveOrUpdate(AssignmentEntity entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(AssignmentEntity entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AssignmentStates getState(AssignmentEntity object) {
        return object.getState();
    }

    @Override
    public boolean existsByName(String subject) {
        return repository.existsBySubject(subject);
    }

    //Логика: получаем поручение, если оно новое,
    // то создаем новую stateMachine еси нет, то восстанавливаем stateMachine из состояния
    @Override
    public StateMachine<AssignmentStates, AssignmentEvents> build(AssignmentEntity assignment) {
        StateMachine<AssignmentStates, AssignmentEvents> stateMachine;
        if (assignment.getState() == null) {
            stateMachine = stateMachineFactory.getStateMachine(String.valueOf(assignment.getId()));
        } else {
            stateMachine = stateMachineFactory.getStateMachine();
            stateMachine
                    .getStateMachineAccessor()
                    .doWithAllRegions(
                            access -> access.resetStateMachine(
                                    new DefaultStateMachineContext<AssignmentStates, AssignmentEvents>(
                                            assignment.getState(), null, null, null, null, String.valueOf(assignment.getId()))));
        }
        return stateMachine;
    }

    //Такая логика: получаем событие и поручение, затем происходит событие,
    // и возвращаем следующее событие за уже сохраненным событием, а не за полученным
    @Override
    public AssignmentEvents sendEvent(AssignmentEvents event, AssignmentEntity assignment) {
        StateMachine<AssignmentStates, AssignmentEvents> stateMachine = this.build(assignment);
        stateMachine.sendEvent(event);
        assignment.setState(stateMachine.getState().getId());
        repository.save(assignment);
        return AssignmentEvents.values()[stateMachine.getState().getId().ordinal()];
    }
}
