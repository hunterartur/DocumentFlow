package com.arturishmaev.documentflow.repository;

import com.arturishmaev.documentflow.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

    Optional<DocumentEntity> findByTitle(String name);
    Optional<DocumentEntity> findAllByContentContaining(String content);
    boolean existsByTitle(String name);
}
