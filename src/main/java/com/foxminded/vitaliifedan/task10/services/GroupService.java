package com.foxminded.vitaliifedan.task10.services;

import com.foxminded.vitaliifedan.task10.dao.GroupDao;
import com.foxminded.vitaliifedan.task10.models.groups.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GroupService {

    private final GroupDao groupDao;

    @Autowired
    public GroupService(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public List<Group> findAll() {
        return groupDao.getAll();
    }

    public Optional<Group> findById(Integer id) {
        return groupDao.getById(id);
    }

    @Transactional
    public Group create(Group group) {
        return groupDao.save(group);
    }

    @Transactional
    public Group update(Group group) {
        return groupDao.save(group);
    }

    @Transactional
    public Boolean deleteById(Integer id) {
        return groupDao.delete(id);
    }
}
