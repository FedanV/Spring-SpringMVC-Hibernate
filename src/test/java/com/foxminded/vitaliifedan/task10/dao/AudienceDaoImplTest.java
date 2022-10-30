package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.containers.BaseDaoTest;
import com.foxminded.vitaliifedan.task10.dao.impl.AudienceDaoImpl;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
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
@Sql(scripts = {"/databaseTestData/audience_table_test_data.sql"}, executionPhase = BEFORE_TEST_METHOD)
class AudienceDaoImplTest extends BaseDaoTest {

    @Autowired
    private EntityManager entityManager;
    private AudienceDao audienceDao;

    @BeforeEach
    void init() {
        audienceDao = new AudienceDaoImpl(entityManager);
    }

    @Test
    void should_CreateAudience() {
        Assertions.assertNotNull(audienceDao.save(Audience.builder().roomNumber(4).build()));
    }

    @Test
    void should_UpdateAudience() {
        Assertions.assertNotNull(audienceDao.save(Audience.builder()
                .id(2)
                .roomNumber(100)
                .build()));
    }

    @Test
    void should_DeleteAudience() {
        Assertions.assertTrue(audienceDao.delete(3));
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
