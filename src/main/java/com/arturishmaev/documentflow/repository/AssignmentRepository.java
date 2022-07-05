package com.arturishmaev.documentflow.repository;

import com.arturishmaev.documentflow.entity.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentEntity, Long> {
    Optional<AssignmentEntity> findByState(String state);
    Optional<AssignmentEntity> findBySubject(String subject);
    boolean existsBySubject(String name);
}
