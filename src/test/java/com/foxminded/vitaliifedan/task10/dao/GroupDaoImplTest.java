package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.containers.BaseDaoTest;
import com.foxminded.vitaliifedan.task10.dao.impl.GroupDaoImpl;
import com.foxminded.vitaliifedan.task10.models.groups.Group;
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
        replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/databaseTestData/groups_table_test_data.sql"}, executionPhase = BEFORE_TEST_METHOD)
class GroupDaoImplTest extends BaseDaoTest {

    @Autowired
    private EntityManager entityManager;
    private GroupDao groupDao;

    @BeforeEach
    void init() {
        groupDao = new GroupDaoImpl(entityManager);
    }

    @Test
    void should_GetAllGroupsFromTable() {
        Assertions.assertEquals(3, groupDao.getAll().size());
    }

    @Test
    void should_CreateGroup() {
        Assertions.assertNotNull(groupDao.save(Group.builder()
                .groupName("group4")
                .build()));
    }

    @Test
    void should_UpdateGroup() {
        Assertions.assertNotNull(groupDao.save(Group.builder()
                .id(2)
                .groupName("group5")
                .build()));
    }

    @Test
    void should_DeleteGroup() {
        Assertions.assertTrue(groupDao.delete(3));
    }

    @Test
    void should_GetGroupById() {
        Assertions.assertTrue(groupDao.getById(2).isPresent());
    }

}
