package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.containers.BaseDaoTest;
import com.foxminded.vitaliifedan.task10.dao.daoImpl.CourseDaoImpl;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.sql.SQLException;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@JdbcTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@Sql(scripts = {"/databaseTestData/course_table_test_data.sql"}, executionPhase = BEFORE_TEST_METHOD)
class CourseDaoImplTest extends BaseDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseDao courseDao;

    @BeforeEach
    void init() {
        courseDao = new CourseDaoImpl(jdbcTemplate);
    }

    @Test
    void should_CreateCourse() throws SQLException {
        Assertions.assertNotNull(courseDao.save(new Course("course4")));
    }

    @Test
    void should_UpdateCourse() throws SQLException {
        Assertions.assertNotNull(courseDao.save(new Course(2, "course10")));
    }

    @Test
    void should_DeleteCourse() throws SQLException {
        Assertions.assertTrue(courseDao.delete(3));
    }

    @Test
    void should_GelAllCourses() {
        Assertions.assertEquals(3, courseDao.getAll().size());
    }

    @Test
    void should_getCourseById() {
        Assertions.assertTrue(courseDao.getById(1).isPresent());
    }
}
