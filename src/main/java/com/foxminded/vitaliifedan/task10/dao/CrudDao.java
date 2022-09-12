package com.foxminded.vitaliifedan.task10.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao <T, K>{

    T save(T entity);
    Boolean delete(K id);
    List<T> getAll();
    Optional<T> getById(K id);
}
