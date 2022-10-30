package com.foxminded.vitaliifedan.task10.models;

import java.io.Serializable;

public interface BaseEntity<T extends Serializable> {

    T getId();

    void setId(T id);

}
