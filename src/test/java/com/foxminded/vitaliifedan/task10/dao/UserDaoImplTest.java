package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.containers.BaseDaoTest;
import com.foxminded.vitaliifedan.task10.dao.impl.UserDaoImpl;
import com.foxminded.vitaliifedan.task10.models.persons.Role;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;
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
@Sql(scripts = "/databaseTestData/users_table_test_data.sql", executionPhase = BEFORE_TEST_METHOD)
class UserDaoImplTest extends BaseDaoTest {

    @Autowired
    private EntityManager entityManager;

    private UserDao userDao;

    @BeforeEach
    void init() {
        userDao = new UserDaoImpl(entityManager);
    }

    @Test
    void should_CreateUser() {
        User user = User.builder()
                .name("name")
                .surname("surname")
                .phone("phone12")
                .login("login")
                .password("pass")
                .role(Role.NONE)
                .build();
        Assertions.assertNotNull(userDao.save(user));
    }

    @Test
    void should_UpdateUser() {
        User user = User.builder()
                .id(1)
                .name("name")
                .surname("surname")
                .phone("phone")
                .login("login")
                .password("pass")
                .role(Role.NONE)
                .build();
        Assertions.assertNotNull(userDao.save(user));
    }

    @Test
    void should_DeleteUser() {
        Assertions.assertTrue(userDao.delete(2));
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
