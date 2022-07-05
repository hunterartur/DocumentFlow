package com.arturishmaev.documentflow;

import com.arturishmaev.documentflow.statemachine.AssignmentEvents;
import com.arturishmaev.documentflow.statemachine.AssignmentStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DocumentFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentFlowApplication.class, args);
    }
}
