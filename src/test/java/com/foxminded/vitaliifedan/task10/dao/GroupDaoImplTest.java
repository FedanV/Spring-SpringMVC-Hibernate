package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.containers.BaseDaoTest;
import com.foxminded.vitaliifedan.task10.dao.daoImpl.GroupDaoImpl;
import com.foxminded.vitaliifedan.task10.models.groups.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@JdbcTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/databaseTestData/groups_table_test_data.sql"}, executionPhase = BEFORE_TEST_METHOD)
class GroupDaoImplTest extends BaseDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private GroupDao groupDao;

    @BeforeEach
    void init() {
        groupDao = new GroupDaoImpl(jdbcTemplate);
    }


    @Test
    void should_GetAllGroupsFromTable() {
        List<Group> all = groupDao.getAll();
        Assertions.assertEquals(3, groupDao.getAll().size());
    }

    @Test
    void should_CreateGroup() {
        Assertions.assertEquals(1, groupDao.create(new Group("test4")));
    }

    @Test
    void should_UpdateGroup() {
        Assertions.assertEquals(1, groupDao.update(new Group(2, "test5")));
    }

    @Test
    void should_DeleteGroup() {
        Assertions.assertEquals(1, groupDao.delete(3));
    }

    @Test
    void should_GetGroupById() {
        List<Group> all = groupDao.getAll();
        Assertions.assertTrue(groupDao.getById(2).isPresent());
    }

}
