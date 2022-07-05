package com.arturishmaev.documentflow.service;

import com.arturishmaev.documentflow.entity.DepartmentEntity;
import com.arturishmaev.documentflow.exception.DepartmentNotFoundException;
import com.arturishmaev.documentflow.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentGeneralServiceImpl implements GeneralService<DepartmentEntity> {

    private static final String DEPARTMENT_NOT_FOUND_BY_ID_MSG = "Department with id = %d not found!";
    private static final String DEPARTMENT_NOT_FOUND_BY_NAME_MSG = "Department with name = %s not found!";

    private final DepartmentRepository repository;

    public DepartmentGeneralServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<DepartmentEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public DepartmentEntity findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new DepartmentNotFoundException(String.format(DEPARTMENT_NOT_FOUND_BY_NAME_MSG, name)));
    }

    @Override
    public DepartmentEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(String.format(DEPARTMENT_NOT_FOUND_BY_ID_MSG, id)));
    }

    @Override
    public DepartmentEntity saveOrUpdate(DepartmentEntity entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(DepartmentEntity entity) {
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
