package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.containers.BaseDaoTest;
import com.foxminded.vitaliifedan.task10.dao.impl.StudentGroupDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityManager;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@Sql(scripts = {"/databaseTestData/groups_table_test_data.sql", "/databaseTestData/student_test_data.sql"},
        executionPhase = BEFORE_TEST_METHOD)
class StudentDaoImplTest extends BaseDaoTest {

    @Autowired
    private EntityManager entityManager;

    private StudentGroupDao studentGroupDao;

    @BeforeEach
    void init() {
        studentGroupDao = new StudentGroupDaoImpl(entityManager);
    }

    @Test
    void should_AddGroupToStudent() {
        Assertions.assertNotNull(studentGroupDao.addGroupToStudent(3, 3));
    }

    @Test
    void should_RemoveStudentFromGroup() {
        Assertions.assertTrue(studentGroupDao.removeStudentFromGroup(2));
    }

    @Test
    void should_updateGroupForStudentId() {
        Assertions.assertNotNull(studentGroupDao.updateGroupForStudentId(1, 3));
    }
}
