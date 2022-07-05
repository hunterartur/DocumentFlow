package com.arturishmaev.documentflow.service;

import java.util.List;

public interface GeneralService<T> {
    List<T> findAll();
    T findByName(String name);
    T findById(Long id);
    T saveOrUpdate(T entity);
    void delete(T entity);
    void deleteById(Long id);
    boolean existsByName(String name);
}
