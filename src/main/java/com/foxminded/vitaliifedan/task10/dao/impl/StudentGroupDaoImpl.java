package com.foxminded.vitaliifedan.task10.dao.impl;

import com.foxminded.vitaliifedan.task10.dao.StudentGroupDao;
import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.persons.StudentGroup;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;


@Component
public class StudentGroupDaoImpl implements StudentGroupDao {

    private static final Logger logger = LoggerFactory.getLogger(StudentGroupDaoImpl.class);

    private final EntityManager entityManager;

    @Autowired
    public StudentGroupDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public StudentGroup addGroupToStudent(int userId, int groupId) {
        logger.debug("Start adding group id={} to student id={}", groupId, userId);
        User user = entityManager.find(User.class, userId);
        Group group = entityManager.find(Group.class, groupId);
        StudentGroup studentGroup = new StudentGroup();
        studentGroup.setGroup(group);
        studentGroup.setUser(user);
        entityManager.persist(studentGroup);
        logger.debug("Finish adding group id={} to student id={}", groupId, userId);
        return studentGroup;
    }

    @Override
    public Boolean removeStudentFromGroup(int userId) {
        logger.debug("Start removing user id={} from group", userId);
        StudentGroup studentGroup = entityManager.createQuery("SELECT sg FROM StudentGroup sg WHERE sg.user.id=:userId", StudentGroup.class)
                .setParameter("userId", userId)
                .getSingleResult();
        entityManager.remove(studentGroup);
        logger.debug("Finish removing user id={} from group", userId);
        return true;
    }

    @Override
    public StudentGroup updateGroupForStudentId(int userId, int groupId) {
        logger.debug("Start updating group id={} for student id={}", groupId, userId);
        StudentGroup studentGroup = entityManager.createQuery("SELECT sg FROM StudentGroup sg WHERE sg.user.id=:userId", StudentGroup.class)
                .setParameter("userId", userId)
                .getSingleResult();
        Group group = entityManager.find(Group.class, groupId);
        studentGroup.setGroup(group);
        logger.debug("Finish updating group id={} for student id={}", groupId, userId);
        return studentGroup;
    }
}
