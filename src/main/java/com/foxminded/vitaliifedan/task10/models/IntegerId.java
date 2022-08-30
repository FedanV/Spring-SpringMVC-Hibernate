package com.foxminded.vitaliifedan.task10.models;

import java.util.Objects;

public abstract class IntegerId implements HasId<Integer> {

    protected Integer id;

    protected IntegerId() {
    }

    protected IntegerId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerId integerId = (IntegerId) o;
        return Objects.equals(id, integerId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
