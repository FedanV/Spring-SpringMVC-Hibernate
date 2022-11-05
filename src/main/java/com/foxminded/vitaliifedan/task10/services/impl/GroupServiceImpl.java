package com.foxminded.vitaliifedan.task10.services.impl;

import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.repositories.GroupRepository;
import com.foxminded.vitaliifedan.task10.services.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Transactional(readOnly = true)
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Group> findById(Integer id) {
        return groupRepository.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Group create(Group group) {
        return groupRepository.save(group);
    }

    @Transactional
    public Group update(Group group) {
        return groupRepository.save(group);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteById(Integer id) {
        groupRepository.deleteById(id);
    }

    @Override
    public Optional<Group> findGroupByName(String name) {
        return groupRepository.findGroupByGroupName(name);
    }
}
