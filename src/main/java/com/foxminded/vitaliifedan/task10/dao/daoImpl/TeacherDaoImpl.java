package com.foxminded.vitaliifedan.task10.dao.daoImpl;

import com.foxminded.vitaliifedan.task10.dao.TeacherDao;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherDaoImpl implements TeacherDao {

    private final JdbcTemplate jdbcTemplate;

    public TeacherDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Teacher addCourseToTeacher(int teacherId, int courseId) throws SQLException {
        String addCourseToTeacher = "INSERT INTO teacher_course(user_id, course_id) VALUES(?, ?)";
        int affectedRow = jdbcTemplate.update(addCourseToTeacher, teacherId, courseId);
        if (affectedRow == 0) {
            throw new SQLException("Course with id " + courseId + " was not added to teacher id " + teacherId);
        }
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course(courseId));
        return new Teacher(teacherId, courses);
    }

    @Override
    public Boolean removeCourseByTeacherId(int teacherId, int courseId) throws SQLException {
        String removeCourseByTeacherId = "DELETE FROM teacher_course WHERE user_id=? AND course_id=?";
        int affectedRow = jdbcTemplate.update(removeCourseByTeacherId, teacherId, courseId);
        if (affectedRow == 0) {
            throw new SQLException("Course with id " + courseId + " was not removed from teacher " + teacherId);
        }
        return true;
    }

    @Override
    public Teacher updateCourseForTeacherId(int teacherId, int newCourseId, int oldCourseId) throws SQLException {
        String updateCourseForTeacherId = "UPDATE teacher_course SET course_id=? WHERE user_id=? AND course_id=?";
        int affectedRow = jdbcTemplate.update(updateCourseForTeacherId, newCourseId, teacherId, oldCourseId);
        if (affectedRow == 0) {
            throw new SQLException("New course id " + newCourseId + " was not added to teacher id " + teacherId +
                    " instead of oldCurseId " + oldCourseId);
        }
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course(newCourseId));
        return new Teacher(teacherId, courses);
    }
}
