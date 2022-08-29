package com.foxminded.vitaliifedan.task10.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao <T, K>{
    K create(T entity);
    K update(T entity);
    K delete(K id);
    List<T> getAll();
    Optional<T> getById(K id);
}
