package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.containers.BaseDaoTest;
import com.foxminded.vitaliifedan.task10.dao.impl.LectureDaoImpl;
import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import com.foxminded.vitaliifedan.task10.models.schedules.Lecture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@JdbcTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@Sql(scripts = {"/databaseTestData/audience_table_test_data.sql", "/databaseTestData/course_table_test_data.sql",
        "/databaseTestData/groups_table_test_data.sql", "/databaseTestData/users_table_test_data.sql",
        "/databaseTestData/lecture_table_test_data.sql"}, executionPhase = BEFORE_TEST_METHOD)
class LectureDaoImplTest extends BaseDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private LectureDao lectureDao;

    @BeforeEach
    void init() {
        lectureDao = new LectureDaoImpl(jdbcTemplate);
    }

    @Test
    void should_CreateLecture() throws SQLException {
        Lecture lecture = new Lecture(new Course(1), new Teacher(1), LocalDate.now(), new Group(1), 1,
                new Audience(1, null));
        Assertions.assertNotNull(lectureDao.save(lecture));
    }

    @Test
    void should_UpdateLecture() throws SQLException {
        Lecture lecture = new Lecture(2, new Course(1), new Teacher(1), LocalDate.now(), new Group(1), 1,
                new Audience(1, null));
        Assertions.assertNotNull(lectureDao.save(lecture));
    }

    @Test
    void should_DeleteLecture() throws SQLException {
        Assertions.assertTrue(lectureDao.delete(3));
    }

    @Test
    void should_getAllLectures() {
        Assertions.assertEquals(3, lectureDao.getAll().size());
    }

    @Test
    void should_getLectureById() {
        Assertions.assertTrue(lectureDao.getById(3).isPresent());
    }

}
