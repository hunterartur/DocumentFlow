package com.arturishmaev.documentflow.service;

import com.arturishmaev.documentflow.entity.OrganizationEntity;
import com.arturishmaev.documentflow.exception.OrganizationNotFoundException;
import com.arturishmaev.documentflow.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationGeneralServiceImpl implements GeneralService<OrganizationEntity> {

    private static final String ORGANIZATION_NOT_FOUND_BY_ID_MSG = "Organization with id = %d not found!";
    private static final String ORGANIZATION_NOT_FOUND_BY_NAME_MSG = "Organization with name = %s not found!";

    private final OrganizationRepository repository;

    public OrganizationGeneralServiceImpl(OrganizationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OrganizationEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public OrganizationEntity findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new OrganizationNotFoundException(String.format(ORGANIZATION_NOT_FOUND_BY_NAME_MSG, name)));
    }

    @Override
    public OrganizationEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException(String.format(ORGANIZATION_NOT_FOUND_BY_ID_MSG, id)));
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public OrganizationEntity saveOrUpdate(OrganizationEntity entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(OrganizationEntity entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
