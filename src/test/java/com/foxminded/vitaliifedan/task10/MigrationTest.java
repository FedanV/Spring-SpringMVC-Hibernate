package com.foxminded.vitaliifedan.task10;

import com.foxminded.vitaliifedan.task10.containers.BaseDaoTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.List;

@JdbcTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE)
class MigrationTest extends BaseDaoTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void should_createAllTablesFromMigrations() throws SQLException {
        List<String> expectedResult = List.of("flyway_schema_history", "group", "user", "lecture", "audience");
        List<String> actualResult = jdbcTemplate.queryForList("SELECT table_name FROM information_schema.tables WHERE table_schema='public'", String.class);
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
