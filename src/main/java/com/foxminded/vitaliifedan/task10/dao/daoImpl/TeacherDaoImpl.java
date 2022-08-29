package com.foxminded.vitaliifedan.task10.dao.daoImpl;

import com.foxminded.vitaliifedan.task10.dao.TeacherDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class TeacherDaoImpl implements TeacherDao {

    private final JdbcTemplate jdbcTemplate;

    public TeacherDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addCourseToTeacher(int teacherId, int courseId) {
        String addCourseToTeacher = "INSERT INTO teacher_course(user_id, course_id) VALUES(?, ?)";
        return jdbcTemplate.update(addCourseToTeacher, teacherId, courseId);
    }

    @Override
    public int removeCourseByTeacherId(int teacherId, int courseId) {
        String removeCourseByTeacherId = "DELETE FROM teacher_course WHERE user_id=? AND course_id=?";
        return jdbcTemplate.update(removeCourseByTeacherId, teacherId, courseId);
    }

    @Override
    public int updateCourseForTeacherId(int teacherId, int newCourseId, int oldCourseId) {
        String updateCourseForTeacherId = "UPDATE teacher_course SET course_id=? WHERE user_id=? AND course_id=?";
        return jdbcTemplate.update(updateCourseForTeacherId, newCourseId, teacherId, oldCourseId);
    }
}
