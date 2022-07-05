package com.arturishmaev.documentflow.service;

import com.arturishmaev.documentflow.entity.DocumentEntity;
import com.arturishmaev.documentflow.exception.DocumentNotFoundException;
import com.arturishmaev.documentflow.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentGeneralServiceImpl implements GeneralService<DocumentEntity> {

    private static final String DOCUMENT_NOT_FOUND_BY_ID_MSG = "Document with id = %d not found!";
    private static final String DOCUMENT_NOT_FOUND_BY_TITLE_MSG = "Document with title = %s not found!";

    private DocumentRepository repository;

    public DocumentGeneralServiceImpl(DocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DocumentEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public DocumentEntity findByName(String name) {
        return repository.findByTitle(name)
                .orElseThrow(() -> new DocumentNotFoundException(String.format(DOCUMENT_NOT_FOUND_BY_TITLE_MSG, name)));
    }

    @Override
    public DocumentEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(String.format(DOCUMENT_NOT_FOUND_BY_ID_MSG, id)));
    }

    @Override
    public DocumentEntity saveOrUpdate(DocumentEntity entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(DocumentEntity entity) {
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
