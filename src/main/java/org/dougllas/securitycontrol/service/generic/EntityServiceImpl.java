package org.dougllas.securitycontrol.service.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class EntityServiceImpl<T, ID extends Serializable, R extends JpaRepository> implements EntityService<T,ID> {

    @Autowired
    private R repository;

    public R getRepository() {
        return repository;
    }

    @Override
    @Transactional
    public T save(T entity) {
        return (T) repository.save(entity);
    }

    @Override
    @Transactional
    public T update(T entity) {
        return (T) repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public Optional<T> findOne(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public List<T> find(T entity) {
        return repository.findAll(Example.of(entity, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING)));
    }
}
