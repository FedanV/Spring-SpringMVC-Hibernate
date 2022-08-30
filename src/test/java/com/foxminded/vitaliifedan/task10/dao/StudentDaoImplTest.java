package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.containers.BaseDaoTest;
import com.foxminded.vitaliifedan.task10.dao.daoImpl.StudentDaoImpl;
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
@Sql(scripts = {"/databaseTestData/users_table_test_data.sql", "/databaseTestData/groups_table_test_data.sql",
        "/databaseTestData/student_group_test_data.sql"},
        executionPhase = BEFORE_TEST_METHOD)
class StudentDaoImplTest extends BaseDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentDao studentDao;

    @BeforeEach
    void init() {
        studentDao = new StudentDaoImpl(jdbcTemplate);
    }

    @Test
    void should_AddGroupToStudent() throws SQLException {
        Assertions.assertNotNull(studentDao.addGroupToStudent(3, 3));
    }

    @Test
    void should_RemoveStudentFromGroup() throws SQLException {
        Assertions.assertTrue(studentDao.removeStudentFromGroup(2));
    }

    @Test
    void should_updateGroupForStudentId() throws SQLException {
        Assertions.assertNotNull(studentDao.updateGroupForStudentId(1, 3));
    }
}
