package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.models.HasId;


public abstract class AbstractCrudDao<T extends HasId<K>, K> implements CrudDao<T, K> {

    @Override
    public T save(T entity) {
        return entity.getId() == null ? create(entity) : update(entity);
    }

    protected abstract T create(T entity);

    protected abstract T update(T entity);
}
