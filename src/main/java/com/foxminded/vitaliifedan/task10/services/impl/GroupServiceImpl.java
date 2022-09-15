package com.foxminded.vitaliifedan.task10.services.impl;

import com.foxminded.vitaliifedan.task10.dao.GroupDao;
import com.foxminded.vitaliifedan.task10.exceptions.GroupException;
import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.services.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    private final GroupDao groupDao;

    @Autowired
    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Transactional(readOnly = true)
    public List<Group> findAll() {
        return groupDao.getAll();
    }

    @Transactional(readOnly = true)
    public Optional<Group> findById(Integer id) {
        return groupDao.getById(id);
    }

    @Transactional
    public Group create(Group group) {
        try {
            return groupDao.save(group);
        } catch (GroupException e) {
            logger.error("Exception happened when Group is creating", e);
        }
        return null;
    }

    @Transactional
    public Group update(Group group) {
        try {
            return groupDao.save(group);
        } catch (GroupException e) {
            logger.error("Exception happened when Group is updating", e);
        }
        return null;
    }

    @Transactional
    public Boolean deleteById(Integer id) {
        try {
            return groupDao.delete(id);
        } catch (GroupException e) {
            logger.error("Exception happened when Group is deleting", e);
        }
        return false;
    }
}
