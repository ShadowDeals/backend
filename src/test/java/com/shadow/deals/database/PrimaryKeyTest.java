package com.shadow.deals.database;

import com.shadow.deals.base.BaseTestContainerTest;
import com.shadow.deals.util.TestUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Kirill "Tamada" Simovin
 */
public class PrimaryKeyTest extends BaseTestContainerTest {

    @Test
    void testInvalidPrimaryKeyUserRole() {
        String invalidRoleId = "fc55ec84-5c0a-4890-9752-cba4c5fa6fa0";

        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {

                String sql = "INSERT INTO sd_user_role(id, role_name) VALUES(?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.fromString(invalidRoleId));
                    statement.setString(2, "NON_EXISTENT_ROLE");
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidPrimaryKeyActivationCode() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_activation_code(id, activation_code, is_activated, sd_user_id) VALUES(?, ?, ?, ?)";

                UUID activationCodeId = TestUtils.createActivationCode(connection, TestUtils.createUser(connection), "CODE1");
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, activationCodeId);
                    statement.setString(2, "some_code");
                    statement.setBoolean(3, false);
                    statement.setObject(4, TestUtils.createUser(connection));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidPrimaryKeyBand() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                TestUtils.createBand(connection);
                TestUtils.createBand(connection);
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidPrimaryKeyBandRequest() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                UUID bandId = TestUtils.createBand(connection);
                UUID userId = TestUtils.createUser(connection);

                String sql = "insert into sd_band_request (id, user_id, band_id) values (?, ?, ?);";

                UUID requestId = UUID.randomUUID();
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, requestId);
                    statement.setObject(2, userId);
                    statement.setObject(3, bandId);
                    statement.execute();
                }

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, requestId);
                    statement.setObject(2, userId);
                    statement.setObject(3, bandId);
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidPrimaryKeyBandTask() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                UUID bandId = TestUtils.createBand(connection);
                UUID taskId = TestUtils.createTask(connection);

                String sql = "insert into sd_band_task (id, band_id, task_id) values (?, ?, ?);";

                UUID bandTaskId = UUID.randomUUID();
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, bandTaskId);
                    statement.setObject(2, bandId);
                    statement.setObject(3, taskId);
                    statement.execute();
                }

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, bandTaskId);
                    statement.setObject(2, bandId);
                    statement.setObject(3, taskId);
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidPrimaryKeyBlockedBand() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                UUID bandId = TestUtils.createBand(connection);

                String sql = "insert into sd_blocked_band (id, band_id) values (?, ?);";

                UUID blockedBandId = UUID.randomUUID();
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, blockedBandId);
                    statement.setObject(2, bandId);
                    statement.execute();
                }

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, blockedBandId);
                    statement.setObject(2, bandId);
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidPrimaryKeyRefreshToken() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "insert into sd_refresh_token (id, refresh_token, user_id) values (?, ?, ?);";

                UUID tokenId = TestUtils.createRefreshToken(connection);
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, tokenId);
                    statement.setString(2, "dwadawdwadaw1");
                    statement.setObject(3, TestUtils.createUser(connection));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidPrimaryKeyRegion() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "insert into sd_region (id, region_name) values (?, ?);";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.fromString("170f5f8f-bf1b-4d1b-ab21-7714a83880f1"));
                    statement.setString(2, "SOME REGION");
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidPrimaryKeyTask() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                UUID taskId = TestUtils.createTask(connection);
                UUID userId = TestUtils.createUser(connection);
                String sql = "INSERT INTO sd_task(id, customer_id, type_id, status_id) VALUES(?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, taskId);
                    statement.setObject(2, userId);
                    statement.setObject(3, UUID.fromString("afa1f1b4-6758-4a0a-a862-570c7fddd3a0"));
                    statement.setObject(4, UUID.fromString("5953308d-d630-4310-8689-023033d4281c"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidPrimaryKeyTaskReport() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                UUID taskId = TestUtils.createTask(connection);
                String sql = "INSERT INTO sd_task_report(id, task_id) VALUES(?, ?)";

                UUID reportId = UUID.randomUUID();
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, reportId);
                    statement.setObject(2, taskId);
                    statement.execute();
                }

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, reportId);
                    statement.setObject(2, taskId);
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidPrimaryKeyTaskStatus() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "insert into sd_task_status (id, task_status) values (?, ?);";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.fromString("5953308d-d630-4310-8689-023033d4281c"));
                    statement.setString(2, "SOME STATUS");
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidPrimaryKeyTaskType() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "insert into sd_task_type (id, task_type) values (?, ?);";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.fromString("59d2457a-cb7f-45aa-bdaf-0d4f8294c3e3"));
                    statement.setString(2, "SOME TYPE");
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidPrimaryKeyUser() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                UUID userId = TestUtils.createUser(connection);

                String sql = "INSERT INTO sd_user(id, email, first_name, last_name, role_id) VALUES(?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, userId);
                    statement.setString(2, "test@example.com");
                    statement.setString(3, "Test");
                    statement.setString(4, "User");
                    statement.setObject(5, UUID.fromString("fc55ec84-5c0a-4890-9752-cba4c5fa6fa0"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }
}
