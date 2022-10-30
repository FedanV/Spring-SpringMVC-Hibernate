package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.containers.BaseDaoTest;
import com.foxminded.vitaliifedan.task10.dao.impl.TeacherCourseDaoImpl;
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
@Sql(scripts = {"/databaseTestData/users_table_test_data.sql", "/databaseTestData/course_table_test_data.sql",
        "/databaseTestData/teacher_course_test_data.sql"}, executionPhase = BEFORE_TEST_METHOD)
class TeacherCourseDaoImplTest extends BaseDaoTest {

    @Autowired
    private EntityManager entityManager;

    private TeacherCourseDao teacherCourseDao;

    @BeforeEach
    void init() {
        teacherCourseDao = new TeacherCourseDaoImpl(entityManager);
    }

    @Test
    void should_AddGroupToStudent() {
        Assertions.assertNotNull(teacherCourseDao.addCourseToTeacher(3, 3));
    }

    @Test
    void should_RemoveStudentFromGroup() {
        Assertions.assertTrue(teacherCourseDao.removeCourseByTeacherId(2, 3));
    }

    @Test
    void should_updateGroupForStudentId() {
        Assertions.assertNotNull(teacherCourseDao.updateCourseForTeacherId(2, 1, 3));
    }
}
