package com.arturishmaev.documentflow.config;

import com.arturishmaev.documentflow.statemachine.AssignmentEvents;
import com.arturishmaev.documentflow.statemachine.AssignmentStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
@Slf4j
public class StatemachineConfig extends StateMachineConfigurerAdapter<AssignmentStates, AssignmentEvents> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<AssignmentStates, AssignmentEvents> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(false)
                .listener(new StateMachineListenerAdapter<AssignmentStates, AssignmentEvents>(){
                    @Override
                    public void stateChanged(State<AssignmentStates, AssignmentEvents> from, State<AssignmentStates, AssignmentEvents> to) {
//                        log.info(String.format("Transition from state %s to state %s"));
                    }

                    @Override
                    public void eventNotAccepted(Message<AssignmentEvents> event) {

                    }
                });
    }

    @Override
    public void configure(StateMachineStateConfigurer<AssignmentStates, AssignmentEvents> states) throws Exception {
        states
                .withStates()
                .initial(AssignmentStates.PREPARATION)
                .end(AssignmentStates.RECEPTION)
                .states(EnumSet.allOf(AssignmentStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<AssignmentStates, AssignmentEvents> transitions) throws Exception {
        transitions
                .withExternal()
                    .source(AssignmentStates.PREPARATION)
                    .target(AssignmentStates.EXECUTION)
                    .event(AssignmentEvents.TAKE_TO_WORK)
                .and()
                .withExternal()
                    .source(AssignmentStates.EXECUTION)
                    .target(AssignmentStates.CONTROL)
                    .event(AssignmentEvents.TAKE_CONTROL)
                .and()
                .withExternal()
                    .source(AssignmentStates.CONTROL)
                    .target(AssignmentStates.RECEPTION)
                    .event(AssignmentEvents.PUBLISH)
                .and()
                .withExternal()
                    .source(AssignmentStates.CONTROL)
                    .target(AssignmentStates.MODIFICATION)
                    .event(AssignmentEvents.REJECT)
                .and()
                .withExternal()
                    .source(AssignmentStates.MODIFICATION)
                    .target(AssignmentStates.EXECUTION)
                    .event(AssignmentEvents.TAKE_TO_WORK);
    }
}
