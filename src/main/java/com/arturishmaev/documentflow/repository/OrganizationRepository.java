package com.arturishmaev.documentflow.repository;

import com.arturishmaev.documentflow.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {
    Optional<OrganizationEntity> findByName(String name);
    boolean existsByName(String name);
}
