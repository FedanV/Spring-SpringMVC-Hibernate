package com.foxminded.vitaliifedan.task10.dao.impl;

import com.foxminded.vitaliifedan.task10.dao.StudentGroupDao;
import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.persons.Student;
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
    public Student addGroupToStudent(int userId, int groupId) {
        logger.debug("Start adding group id={} to student id={}", groupId, userId);
        Student student = entityManager.find(Student.class, userId);
        Group group = entityManager.find(Group.class, groupId);
        student.setGroup(group);
        entityManager.persist(student);
        logger.debug("Finish adding group id={} to student id={}", groupId, userId);
        return student;
    }

    @Override
    public Boolean removeStudentFromGroup(int userId) {
        logger.debug("Start removing user id={} from group", userId);
        Student student = entityManager.createQuery("SELECT s FROM Student s WHERE s.id=:userId", Student.class)
                .setParameter("userId", userId)
                .getSingleResult();
        entityManager.remove(student);
        logger.debug("Finish removing user id={} from group", userId);
        return true;
    }

    @Override
    public Student updateGroupForStudentId(int userId, int groupId) {
        logger.debug("Start updating group id={} for student id={}", groupId, userId);
        Student student = entityManager.createQuery("SELECT s FROM Student s WHERE s.id=:userId", Student.class)
                .setParameter("userId", userId)
                .getSingleResult();
        Group group = entityManager.find(Group.class, groupId);
        student.setGroup(group);
        logger.debug("Finish updating group id={} for student id={}", groupId, userId);
        return student;
    }

}
