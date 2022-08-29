package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.containers.BaseDaoTest;
import com.foxminded.vitaliifedan.task10.dao.daoImpl.TeacherDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@JdbcTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@Sql(scripts = {"/databaseTestData/users_table_test_data.sql", "/databaseTestData/course_table_test_data.sql",
        "/databaseTestData/teacher_course_test_data.sql"}, executionPhase = BEFORE_TEST_METHOD)
class TeacherDaoImplTest extends BaseDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private TeacherDao teacherDao;

    @BeforeEach
    void init() {
        teacherDao = new TeacherDaoImpl(jdbcTemplate);
    }

    @Test
    void should_AddGroupToStudent() {
        Assertions.assertEquals(1, teacherDao.addCourseToTeacher(3, 3));
    }

    @Test
    void should_RemoveStudentFromGroup() {
        Assertions.assertEquals(1, teacherDao.removeCourseByTeacherId(2, 3));
    }

    @Test
    void should_updateGroupForStudentId() {
        Assertions.assertEquals(1, teacherDao.updateCourseForTeacherId(2, 1, 3));
    }
}
