package com.foxminded.vitaliifedan.task10.dao.impl;

import com.foxminded.vitaliifedan.task10.dao.TeacherDao;
import com.foxminded.vitaliifedan.task10.exceptions.UserException;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TeacherDaoImpl implements TeacherDao {

    private static final Logger logger = LoggerFactory.getLogger(TeacherDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TeacherDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Teacher addCourseToTeacher(int teacherId, int courseId) {
        logger.info("Start adding course id={} to teacher id={}", courseId, teacherId);
        String addCourseToTeacher = "INSERT INTO teacher_course(user_id, course_id) VALUES(?, ?)";
        int affectedRow = jdbcTemplate.update(addCourseToTeacher, teacherId, courseId);
        if (affectedRow == 0) {
            throw new UserException("Course with id " + courseId + " was not added to teacher id " + teacherId);
        }
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course(courseId));
        logger.info("Finish adding course id={} to teacher id={}", courseId, teacherId);
        return new Teacher(teacherId, courses);
    }

    @Override
    public Boolean removeCourseByTeacherId(int teacherId, int courseId) {
        logger.info("Start deletion course id={} for teacher id={}", courseId, teacherId);
        String removeCourseByTeacherId = "DELETE FROM teacher_course WHERE user_id=? AND course_id=?";
        int affectedRow = jdbcTemplate.update(removeCourseByTeacherId, teacherId, courseId);
        if (affectedRow == 0) {
            throw new UserException("Course with id " + courseId + " was not removed from teacher " + teacherId);
        }
        logger.info("Finish deletion course id={} for teacher id={}", courseId, teacherId);
        return true;
    }

    @Override
    public Teacher updateCourseForTeacherId(int teacherId, int newCourseId, int oldCourseId) {
        logger.info("Start updating course id={} for teacher id={}", oldCourseId, teacherId);
        String updateCourseForTeacherId = "UPDATE teacher_course SET course_id=? WHERE user_id=? AND course_id=?";
        int affectedRow = jdbcTemplate.update(updateCourseForTeacherId, newCourseId, teacherId, oldCourseId);
        if (affectedRow == 0) {
            throw new UserException("New course id " + newCourseId + " was not added to teacher id " + teacherId +
                    " instead of oldCurseId " + oldCourseId);
        }
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course(newCourseId));
        logger.info("Finish updating course id={} for teacher id={}", oldCourseId, teacherId);
        return new Teacher(teacherId, courses);
    }
}
