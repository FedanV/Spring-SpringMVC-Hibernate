package com.foxminded.vitaliifedan.task10.dao.daoImpl;


import com.foxminded.vitaliifedan.task10.dao.CourseDao;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class CourseDaoImpl implements CourseDao {

    private final JdbcTemplate jdbcTemplate;

    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer create(Course entity) {
        String createCourse = "INSERT INTO course(course_name) VALUES(?)";
        return jdbcTemplate.update(createCourse, entity.getCourseName());
    }

    @Override
    public Integer update(Course entity) {
        String updateCourse = "UPDATE course SET course_name=? WHERE id=?";
        return jdbcTemplate.update(updateCourse, entity.getCourseName(), entity.getId());
    }

    @Override
    public Integer delete(Integer id) {
        String deleteCourse = "DELETE FROM course WHERE id=?";
        return jdbcTemplate.update(deleteCourse, id);
    }

    @Override
    public List<Course> getAll() {
        String getAllCourses = "SELECT * FROM course";
        return jdbcTemplate.query(getAllCourses, new BeanPropertyRowMapper<>(Course.class));
    }

    @Override
    public Optional<Course> getById(Integer id) {
        String getCourseById = "SELECT * FROM course WHERE id=?";
        return jdbcTemplate.query(getCourseById, new BeanPropertyRowMapper<>(Course.class), id)
                .stream().findFirst();
    }
}
