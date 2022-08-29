package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.containers.BaseDaoTest;
import com.foxminded.vitaliifedan.task10.dao.daoImpl.AudienceDaoImpl;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
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
@Sql(scripts = {"/databaseTestData/audience_table_test_data.sql"}, executionPhase = BEFORE_TEST_METHOD)
class AudienceDaoImplTest extends BaseDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private AudienceDao audienceDao;

    @BeforeEach
    void init() {
        audienceDao = new AudienceDaoImpl(jdbcTemplate);
    }

    @Test
    void should_CreateAudience() {
        Assertions.assertEquals(1, audienceDao.create(new Audience(4)));
    }

    @Test
    void should_UpdateAudience() {
        Assertions.assertEquals(1, audienceDao.update(new Audience(2, 100)));
    }

    @Test
    void should_DeleteAudience() {
        Assertions.assertEquals(1, audienceDao.delete(3));
    }

    @Test
    void should_GetAllAudiences() {
        Assertions.assertEquals(3, audienceDao.getAll().size());
    }

    @Test
    void should_GetAudienceById() {
        Assertions.assertTrue(audienceDao.getById(3).isPresent());
    }


}