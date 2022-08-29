package com.foxminded.vitaliifedan.task10.dao.daoImpl;

import com.foxminded.vitaliifedan.task10.dao.StudentDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class StudentDaoImpl implements StudentDao {

    private final JdbcTemplate jdbcTemplate;

    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addGroupToStudent(int userId, int groupId) {
        String addGroupToStudent = "INSERT INTO student_group(user_id, group_id) VALUES(?, ?)";
        return jdbcTemplate.update(addGroupToStudent, userId, groupId);
    }

    @Override
    public int removeStudentFromGroup(int userId) {
        String removeStudentFromGroup = "DELETE FROM student_group WHERE user_id=?";
        return jdbcTemplate.update(removeStudentFromGroup, userId);
    }

    @Override
    public int updateGroupForStudentId(int userId, int groupId) {
        String updateGroupForStudentId = "UPDATE student_group SET group_id=? WHERE user_id=?";
        return jdbcTemplate.update(updateGroupForStudentId, groupId, userId);
    }
}
