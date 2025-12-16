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
public class ForeignKeyTest extends BaseTestContainerTest {

    @Test
    void testInvalidForeignKeyActivationCode() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {

                TestUtils.createActivationCode(connection, UUID.randomUUID());
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyBand() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {

                TestUtils.createBand(connection, UUID.fromString("1704041f-54ea-46c9-8c22-11bf60fefb73"));
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyBandRequest1() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                UUID userId = TestUtils.createUser(connection);
                TestUtils.createBandRequest(connection, UUID.fromString("1704041f-54ea-46c9-8c22-11bf60fefb73"), userId);
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyBandRequest2() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                UUID bandId = TestUtils.createBand(connection);
                TestUtils.createBandRequest(connection, bandId, UUID.fromString("1704041f-54ea-46c9-8c22-11bf60fefb73"));
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyBandTask1() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                UUID bandId = TestUtils.createBand(connection);
                TestUtils.createBandTask(connection, bandId, UUID.fromString("1704041f-54ea-46c9-8c22-11bf60fefb73"));
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyBandTask2() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                UUID taskId = TestUtils.createTask(connection);
                TestUtils.createBandTask(connection, UUID.fromString("1704041f-54ea-46c9-8c22-11bf60fefb73"), taskId);
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyBlockedBand() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                TestUtils.createBlockedBand(connection, UUID.fromString("1704041f-54ea-46c9-8c22-11bf60fefb73"));
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyRefreshToken() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                TestUtils.createRefreshToken(connection, UUID.randomUUID());
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyTaskCancelStatus() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                TestUtils.createTask(
                    connection,
                    TestUtils.createUser(connection),
                    UUID.fromString("afa1f1b4-6758-4a0a-a862-570c7fddd3a0"),
                    UUID.fromString("5953308d-d630-4310-8689-023033d4281c"),
                    TestUtils.createUser(connection),
                    UUID.randomUUID()
                );
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyTaskOfficer() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                TestUtils.createTask(
                    connection,
                    TestUtils.createUser(connection),
                    UUID.fromString("afa1f1b4-6758-4a0a-a862-570c7fddd3a0"),
                    UUID.fromString("5953308d-d630-4310-8689-023033d4281c"),
                    UUID.randomUUID(),
                    UUID.fromString("d93a9343-4769-4983-92df-55be734540a7")
                );
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyTaskStatus() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                TestUtils.createTask(
                    connection,
                    TestUtils.createUser(connection),
                    UUID.fromString("afa1f1b4-6758-4a0a-a862-570c7fddd3a0"),
                    UUID.randomUUID(),
                    TestUtils.createUser(connection),
                    UUID.fromString("d93a9343-4769-4983-92df-55be734540a7")
                );
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyTaskType() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                TestUtils.createTask(
                    connection,
                    TestUtils.createUser(connection),
                    UUID.randomUUID(),
                    UUID.fromString("5953308d-d630-4310-8689-023033d4281c"),
                    TestUtils.createUser(connection),
                    UUID.fromString("d93a9343-4769-4983-92df-55be734540a7")
                );
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyCustomer() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                TestUtils.createTask(
                    connection,
                    UUID.randomUUID(),
                    UUID.fromString("afa1f1b4-6758-4a0a-a862-570c7fddd3a0"),
                    UUID.fromString("5953308d-d630-4310-8689-023033d4281c"),
                    TestUtils.createUser(connection),
                    UUID.fromString("d93a9343-4769-4983-92df-55be734540a7")
                );
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyTaskReport() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_task_report(id, task_id) VALUES(?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setObject(2, UUID.randomUUID());
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyUserBand() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {

                String sql = "INSERT INTO sd_user(id, email, first_name, last_name, band_id, role_id) VALUES(?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "test@example.com");
                    statement.setString(3, "Test");
                    statement.setString(4, "User");
                    statement.setString(4, "User");
                    statement.setObject(5, UUID.randomUUID());
                    statement.setObject(6, UUID.fromString("fc55ec84-5c0a-4890-9752-cba4c5fa6fa0"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyUserRole() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {

                String sql = "INSERT INTO sd_user(id, email, first_name, last_name, role_id) VALUES(?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "test@example.com");
                    statement.setString(3, "Test");
                    statement.setString(4, "User");
                    statement.setObject(5, UUID.randomUUID());
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyUserTask() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {

                String sql = "INSERT INTO sd_user(id, email, first_name, last_name, role_id, task_id) VALUES(?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "test@example.com");
                    statement.setString(3, "Test");
                    statement.setString(4, "User");
                    statement.setObject(5, UUID.fromString("fc55ec84-5c0a-4890-9752-cba4c5fa6fa0"));
                    statement.setObject(6, UUID.randomUUID());
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyUserOwnBand() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {

                String sql = "INSERT INTO sd_user(id, email, first_name, last_name, role_id, own_band_id) VALUES(?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "test@example.com");
                    statement.setString(3, "Test");
                    statement.setString(4, "User");
                    statement.setObject(5, UUID.fromString("fc55ec84-5c0a-4890-9752-cba4c5fa6fa0"));
                    statement.setObject(6, UUID.randomUUID());
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidForeignKeyUserRegion() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {

                String sql = "INSERT INTO sd_user(id, email, first_name, last_name, role_id, region_id) VALUES(?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "test@example.com");
                    statement.setString(3, "Test");
                    statement.setString(4, "User");
                    statement.setObject(5, UUID.fromString("fc55ec84-5c0a-4890-9752-cba4c5fa6fa0"));
                    statement.setObject(6, UUID.randomUUID());
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }
}
