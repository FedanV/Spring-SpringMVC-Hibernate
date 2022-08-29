package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.containers.BaseDaoTest;
import com.foxminded.vitaliifedan.task10.dao.daoImpl.UserDaoImpl;
import com.foxminded.vitaliifedan.task10.models.persons.Role;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;
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
@Sql(scripts = "/databaseTestData/users_table_test_data.sql", executionPhase = BEFORE_TEST_METHOD)
class UserDaoImplTest extends BaseDaoTest {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserDao userDao;

    @BeforeEach
    void init() {
        userDao = new UserDaoImpl(jdbcTemplate);
    }

    @Test
    void should_CreateUser() {
        Assertions.assertEquals(1, userDao.create(new User("login", "pass", Role.ROLE_ADMIN, UserType.USER)));
    }

    @Test
    void should_UpdateUser() {
        Assertions.assertEquals(1, userDao.update(new User(1, "new Login", "new Pass", Role.ROLE_STUDENT, UserType.STUDENT)));
    }

    @Test
    void should_DeleteUser() {
        Assertions.assertEquals(1, userDao.delete(2));
    }

    @Test
    void should_getAllUsers() {
        Assertions.assertEquals(3, userDao.getAll().size());
    }

    @Test
    void should_getUserById() {
        Assertions.assertTrue(userDao.getById(2).isPresent());
    }

}