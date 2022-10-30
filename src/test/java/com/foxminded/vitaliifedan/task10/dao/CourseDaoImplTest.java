package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.containers.BaseDaoTest;
import com.foxminded.vitaliifedan.task10.dao.impl.CourseDaoImpl;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityManager;
import java.sql.SQLException;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@Sql(scripts = {"/databaseTestData/course_table_test_data.sql"}, executionPhase = BEFORE_TEST_METHOD)
class CourseDaoImplTest extends BaseDaoTest {

    @Autowired
    private EntityManager entityManager;

    private CourseDao courseDao;

    @BeforeEach
    void init() {
        courseDao = new CourseDaoImpl(entityManager);
    }

    @Test
    void should_CreateCourse() {
        Assertions.assertNotNull(courseDao.save(Course.builder().courseName("course4").build()));
    }

    @Test
    void should_UpdateCourse() {
        Assertions.assertNotNull(courseDao.save(Course.builder()
                .id(2)
                .courseName("test2")
                .build()));
    }

    @Test
    void should_DeleteCourse() {
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
