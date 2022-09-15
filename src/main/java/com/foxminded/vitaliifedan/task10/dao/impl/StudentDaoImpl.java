package com.foxminded.vitaliifedan.task10.dao.impl;

import com.foxminded.vitaliifedan.task10.dao.StudentDao;
import com.foxminded.vitaliifedan.task10.exceptions.UserException;
import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.persons.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class StudentDaoImpl implements StudentDao {

    private static final Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Student addGroupToStudent(int userId, int groupId) {
        logger.info("Start adding group id={} to student id={}", groupId, userId);
        String addGroupToStudent = "INSERT INTO student_group(user_id, group_id) VALUES(?, ?)";
        int affectedRow = jdbcTemplate.update(addGroupToStudent, userId, groupId);
        if (affectedRow == 0) {
            throw new UserException("Group with id " + groupId + " was not added to user id " + userId);
        }
        logger.info("Finish adding group id={} to student id={}", groupId, userId);
        return new Student(userId, new Group(groupId));
    }

    @Override
    public Boolean removeStudentFromGroup(int userId) {
        logger.info("Start removing user id={} from group", userId);
        String removeStudentFromGroup = "DELETE FROM student_group WHERE user_id=?";
        int affectedRow = jdbcTemplate.update(removeStudentFromGroup, userId);
        if (affectedRow == 0) {
            throw new UserException("Student with id " + userId + " was not removed from table student_group");
        }
        logger.info("Finish removing user id={} from group", userId);
        return true;
    }

    @Override
    public Student updateGroupForStudentId(int userId, int groupId) {
        logger.info("Start updating group id={} for student id={}", groupId, userId);
        String updateGroupForStudentId = "UPDATE student_group SET group_id=? WHERE user_id=?";
        int affectedRow = jdbcTemplate.update(updateGroupForStudentId, groupId, userId);
        if (affectedRow == 0) {
            throw new UserException("Group with id " + groupId + " was not updated for user id " + userId);
        }
        logger.info("Finish updating group id={} for student id={}", groupId, userId);
        return new Student(userId, new Group(groupId));
    }
}
