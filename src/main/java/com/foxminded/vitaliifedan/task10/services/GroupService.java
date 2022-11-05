package com.foxminded.vitaliifedan.task10.services;

import com.foxminded.vitaliifedan.task10.models.groups.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    List<Group> findAll();

    Optional<Group> findById(Integer id);

    Group create(Group group);

    Group update(Group group);

    void deleteById(Integer id);
    Optional<Group> findGroupByName(String name);
}
