package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.models.HasId;

import java.sql.SQLException;

public abstract class AbstractCrudDao<T extends HasId<K>, K> implements CrudDao<T, K> {

    @Override
    public T save(T entity) throws SQLException {
        return entity.getId() == null ? create(entity) : update(entity);
    }

    protected abstract T create(T entity) throws SQLException;
    protected abstract T update(T entity) throws SQLException;
}
