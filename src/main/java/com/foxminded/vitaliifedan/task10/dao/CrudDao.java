package com.foxminded.vitaliifedan.task10.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDao <T, K>{

    T save(T entity) throws SQLException;
    Boolean delete(K id) throws SQLException;
    List<T> getAll();
    Optional<T> getById(K id);
}
