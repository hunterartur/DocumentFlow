package com.arturishmaev.documentflow.service;

import com.arturishmaev.documentflow.entity.RoleEntity;
import com.arturishmaev.documentflow.exception.RoleNotFoundException;
import com.arturishmaev.documentflow.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleGeneralServiceImpl implements GeneralService<RoleEntity> {

    private static final String ROLE_NOT_FOUND_BY_ID_MSG = "Role with id = %d not found!";
    private static final String ROLE_NOT_FOUND_BY_NAME_MSG = "Role with name = %s not found!";

    private RoleRepository repository;

    public RoleGeneralServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RoleEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public RoleEntity findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException(String.format(ROLE_NOT_FOUND_BY_NAME_MSG, name)));
    }

    @Override
    public RoleEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(String.format(ROLE_NOT_FOUND_BY_ID_MSG, id)));
    }

    @Override
    public RoleEntity saveOrUpdate(RoleEntity entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(RoleEntity entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
