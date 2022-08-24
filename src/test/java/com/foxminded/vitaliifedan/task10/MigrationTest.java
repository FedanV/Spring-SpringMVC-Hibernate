package com.foxminded.vitaliifedan.task10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class MigrationTest {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;

    @Test
    void should_createAllTablesFromMigrations() throws SQLException {
        List<String> expectedResult = List.of("audience", "flyway_schema_history", "group", "lecture", "user");
        List<String> actualResult = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String showTables = "SHOW TABLES";
            try(PreparedStatement statement = conn.prepareStatement(showTables)) {
                try(ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        actualResult.add(resultSet.getString("table_name"));
                    }
                }
            }
        }
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
