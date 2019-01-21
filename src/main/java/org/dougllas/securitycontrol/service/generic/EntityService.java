package org.dougllas.securitycontrol.service.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface EntityService<T, ID extends Serializable> {

    T save(T entity);
    T update(T entity);

    void delete(T entity);

    Optional<T> findOne(ID id);
    List<T> findAll();
    List<T> find(T entity);

}
