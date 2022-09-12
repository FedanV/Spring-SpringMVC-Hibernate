package com.foxminded.vitaliifedan.task10.dao.daoImpl;


import com.foxminded.vitaliifedan.task10.dao.AbstractCrudDao;
import com.foxminded.vitaliifedan.task10.dao.CourseDao;
import com.foxminded.vitaliifedan.task10.exceptions.CourseException;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
public class CourseDaoImpl extends AbstractCrudDao<Course, Integer> implements CourseDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected Course create(Course entity) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String createCourse = "INSERT INTO course(course_name) VALUES(?)";
        int affectedRow = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(createCourse, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getCourseName());
            return statement;
        }, keyHolder);
        if (affectedRow == 0) {
            throw new CourseException("Course " + entity.getCourseName() + " was not create");
        }
        int id = (int) keyHolder.getKeys().get("id");
        return new Course(id, entity.getCourseName());
    }

    @Override
    protected Course update(Course entity) {
        String updateCourse = "UPDATE course SET course_name=? WHERE id=?";
        int affectedRow = jdbcTemplate.update(updateCourse, entity.getCourseName(), entity.getId());
        if (affectedRow == 0) {
            throw new CourseException("Course " + entity.getCourseName() + " was not updated");
        }
        return new Course(entity.getId(), entity.getCourseName());
    }

    @Override
    public Boolean delete(Integer id) {
        String deleteCourse = "DELETE FROM course WHERE id=?";
        int affectedRow = jdbcTemplate.update(deleteCourse, id);
        if (affectedRow == 0) {
            throw new CourseException("Course with id " + id + " was not deleted");
        }
        return true;
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
