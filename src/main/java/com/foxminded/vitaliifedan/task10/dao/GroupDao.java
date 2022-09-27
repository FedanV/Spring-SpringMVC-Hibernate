package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.models.groups.Group;

import java.util.Optional;

public interface GroupDao extends CrudDao<Group, Integer>{

    Optional<Group> findGroupByGroupName(String name);
}
