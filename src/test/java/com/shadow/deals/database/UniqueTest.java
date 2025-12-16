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
public class UniqueTest extends BaseTestContainerTest {

    @Test
    void testNonUniqueActivationCode1() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                UUID userId = TestUtils.createUser(connection);

                TestUtils.createActivationCode(connection, userId, "CODE1");
                TestUtils.createActivationCode(connection, userId, "CODE2");
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testNonUniqueBand() {
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
    void testNonUniqueBandRequest() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                UUID bandId = TestUtils.createBand(connection);
                UUID userId = TestUtils.createUser(connection);

                TestUtils.createBandRequest(connection, bandId, userId);
                TestUtils.createBandRequest(connection, bandId, userId);
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testNonUniqueBandTask() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                UUID bandId = TestUtils.createBand(connection);
                UUID userId = TestUtils.createUser(connection);

                TestUtils.createBandTask(connection, bandId, userId);
                TestUtils.createBandTask(connection, bandId, userId);
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testNonUniqueBlockedBand() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                UUID bandId = TestUtils.createBand(connection);

                TestUtils.createBlockedBand(connection, bandId);
                TestUtils.createBlockedBand(connection, bandId);
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testNonUniqueTaskReport() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                UUID taskId = TestUtils.createTask(connection);

                TestUtils.createTaskReport(connection, taskId);
                TestUtils.createTaskReport(connection, taskId);
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testNonUniqueTaskStatus() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "insert into sd_task_status (id, task_status) values (?, ?);";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "WAITING_FOR_ACCEPT");
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testNonUniqueTaskType() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "insert into sd_task_type (id, task_type) values (?, ?);";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "HIJACKING");
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testNonUniqueRegion() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "insert into sd_region (id, region_name) values (?, ?);";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "MOSCOW_REGION");
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testNonUniqueUserRole() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {

                String sql = "INSERT INTO sd_user_role(id, role_name) VALUES(?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SUPER_ADMIN");
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23505", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("duplicate key value violates unique constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }
}
