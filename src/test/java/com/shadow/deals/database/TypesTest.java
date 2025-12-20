package com.shadow.deals.database;

import com.shadow.deals.base.BaseTestContainerTest;
import com.shadow.deals.util.TestUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Kirill "Tamada" Simovin
 */
public class TypesTest extends BaseTestContainerTest {

    @Test
    void testInvalidTypeActivationCode() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_activation_code(id, activation_code, is_activated, sd_user_id) VALUES(?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_CODE");
                    statement.setBoolean(3, false);
                    statement.setObject(4, "SOME_USER_ID");
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type uuid but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeBand() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_band(id, region_id) VALUES(?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_REGION");
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type uuid but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeBandTask() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_band_task(id, band_id, task_id) VALUES(?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_BAND");
                    statement.setString(3, "SOME_TASK");
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type uuid but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeBlockedBand() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_blocked_band(id, band_id) VALUES(?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_BAND");
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type uuid but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeRefreshTokenRefreshToken() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_refresh_token(id, refresh_token, date_created, user_id) VALUES(?, ?, ?, ?)";

                Date date = new Date(System.currentTimeMillis());

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, UUID.randomUUID().toString().repeat(10));
                    statement.setDate(3, date);
                    statement.setObject(4, TestUtils.createUser(connection));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("22001", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("value too long for type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeRefreshTokenDateCreated() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_refresh_token(id, refresh_token, date_created, user_id) VALUES(?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, UUID.randomUUID().toString());
                    statement.setString(3, "DATE");
                    statement.setObject(4, TestUtils.createUser(connection));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type timestamp without time zone but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeRefreshTokenUserId() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_refresh_token(id, refresh_token, date_created, user_id) VALUES(?, ?, ?, ?)";

                Date date = new Date(System.currentTimeMillis());

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, UUID.randomUUID().toString());
                    statement.setDate(3, date);
                    statement.setString(4, "SOME_USER");
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type uuid but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeRegion() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_region(id, region_name) VALUES(?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_REGION".repeat(40));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("22001", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("value too long for type character varying(255)"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeTaskAddress() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_task(id, address, description, customer_id, type_id, status_id, date_created, officer_id, price, cancel_reason, cancel_status_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_ADDRESS".repeat(400));
                    statement.setString(3, "SOME_DESCRIPTION");
                    statement.setObject(4, TestUtils.createUser(connection));
                    statement.setObject(5, UUID.fromString("59d2457a-cb7f-45aa-bdaf-0d4f8294c3e3"));
                    statement.setObject(6, UUID.fromString("5a2a3e7c-9406-4226-a393-a5e04d56e680"));
                    statement.setDate(7, new Date(System.currentTimeMillis()));
                    statement.setObject(8, TestUtils.createUser(connection));
                    statement.setInt(9, 10);
                    statement.setString(10, "SOME_REASON");
                    statement.setObject(11, UUID.fromString("d93a9343-4769-4983-92df-55be734540a7"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("22001", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("value too long for type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeTaskDescription() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_task(id, address, description, customer_id, type_id, status_id, date_created, officer_id, price, cancel_reason, cancel_status_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_ADDRESS");
                    statement.setString(3, "SOME_DESCRIPTION".repeat(400));
                    statement.setObject(4, TestUtils.createUser(connection));
                    statement.setObject(5, UUID.fromString("59d2457a-cb7f-45aa-bdaf-0d4f8294c3e3"));
                    statement.setObject(6, UUID.fromString("5a2a3e7c-9406-4226-a393-a5e04d56e680"));
                    statement.setDate(7, new Date(System.currentTimeMillis()));
                    statement.setObject(8, TestUtils.createUser(connection));
                    statement.setInt(9, 10);
                    statement.setString(10, "SOME_REASON");
                    statement.setObject(11, UUID.fromString("d93a9343-4769-4983-92df-55be734540a7"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("22001", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("value too long for type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeTaskCustomerId() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_task(id, address, description, customer_id, type_id, status_id, date_created, officer_id, price, cancel_reason, cancel_status_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_ADDRESS");
                    statement.setString(3, "SOME_DESCRIPTION");
                    statement.setString(4, "SOME_USER");
                    statement.setObject(5, UUID.fromString("59d2457a-cb7f-45aa-bdaf-0d4f8294c3e3"));
                    statement.setObject(6, UUID.fromString("5a2a3e7c-9406-4226-a393-a5e04d56e680"));
                    statement.setDate(7, new Date(System.currentTimeMillis()));
                    statement.setObject(8, TestUtils.createUser(connection));
                    statement.setInt(9, 10);
                    statement.setString(10, "SOME_REASON");
                    statement.setObject(11, UUID.fromString("d93a9343-4769-4983-92df-55be734540a7"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type uuid but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeTaskTypeId() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_task(id, address, description, customer_id, type_id, status_id, date_created, officer_id, price, cancel_reason, cancel_status_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_ADDRESS");
                    statement.setString(3, "SOME_DESCRIPTION");
                    statement.setObject(4, TestUtils.createUser(connection));
                    statement.setString(5, "SOME_TYPE");
                    statement.setObject(6, UUID.fromString("5a2a3e7c-9406-4226-a393-a5e04d56e680"));
                    statement.setDate(7, new Date(System.currentTimeMillis()));
                    statement.setObject(8, TestUtils.createUser(connection));
                    statement.setInt(9, 10);
                    statement.setString(10, "SOME_REASON");
                    statement.setObject(11, UUID.fromString("d93a9343-4769-4983-92df-55be734540a7"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type uuid but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeTaskStatusId() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_task(id, address, description, customer_id, type_id, status_id, date_created, officer_id, price, cancel_reason, cancel_status_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_ADDRESS");
                    statement.setString(3, "SOME_DESCRIPTION");
                    statement.setObject(4, TestUtils.createUser(connection));
                    statement.setObject(5, UUID.fromString("59d2457a-cb7f-45aa-bdaf-0d4f8294c3e3"));
                    statement.setString(6, "SOME_STATUS");
                    statement.setDate(7, new Date(System.currentTimeMillis()));
                    statement.setObject(8, TestUtils.createUser(connection));
                    statement.setInt(9, 10);
                    statement.setString(10, "SOME_REASON");
                    statement.setObject(11, UUID.fromString("d93a9343-4769-4983-92df-55be734540a7"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type uuid but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeTaskDateCreated() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_task(id, address, description, customer_id, type_id, status_id, date_created, officer_id, price, cancel_reason, cancel_status_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_ADDRESS");
                    statement.setString(3, "SOME_DESCRIPTION");
                    statement.setObject(4, TestUtils.createUser(connection));
                    statement.setObject(5, UUID.fromString("59d2457a-cb7f-45aa-bdaf-0d4f8294c3e3"));
                    statement.setObject(6, UUID.fromString("5a2a3e7c-9406-4226-a393-a5e04d56e680"));
                    statement.setString(7, "SOME_DATE");
                    statement.setObject(8, TestUtils.createUser(connection));
                    statement.setInt(9, 10);
                    statement.setString(10, "SOME_REASON");
                    statement.setObject(11, UUID.fromString("d93a9343-4769-4983-92df-55be734540a7"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type timestamp without time zone but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeTaskOfficerId() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_task(id, address, description, customer_id, type_id, status_id, date_created, officer_id, price, cancel_reason, cancel_status_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_ADDRESS");
                    statement.setString(3, "SOME_DESCRIPTION");
                    statement.setObject(4, TestUtils.createUser(connection));
                    statement.setObject(5, UUID.fromString("59d2457a-cb7f-45aa-bdaf-0d4f8294c3e3"));
                    statement.setObject(6, UUID.fromString("5a2a3e7c-9406-4226-a393-a5e04d56e680"));
                    statement.setDate(7, new Date(System.currentTimeMillis()));
                    statement.setString(8, "SOME_USER");
                    statement.setInt(9, 10);
                    statement.setString(10, "SOME_REASON");
                    statement.setObject(11, UUID.fromString("d93a9343-4769-4983-92df-55be734540a7"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type uuid but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeTaskPrice() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_task(id, address, description, customer_id, type_id, status_id, date_created, officer_id, price, cancel_reason, cancel_status_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_ADDRESS");
                    statement.setString(3, "SOME_DESCRIPTION");
                    statement.setObject(4, TestUtils.createUser(connection));
                    statement.setObject(5, UUID.fromString("59d2457a-cb7f-45aa-bdaf-0d4f8294c3e3"));
                    statement.setObject(6, UUID.fromString("5a2a3e7c-9406-4226-a393-a5e04d56e680"));
                    statement.setDate(7, new Date(System.currentTimeMillis()));
                    statement.setObject(8, TestUtils.createUser(connection));
                    statement.setString(9, "SOME_PRICE");
                    statement.setString(10, "SOME_REASON");
                    statement.setObject(11, UUID.fromString("d93a9343-4769-4983-92df-55be734540a7"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type integer but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeTaskCancelReason() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_task(id, address, description, customer_id, type_id, status_id, date_created, officer_id, price, cancel_reason, cancel_status_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_ADDRESS");
                    statement.setString(3, "SOME_DESCRIPTION");
                    statement.setObject(4, TestUtils.createUser(connection));
                    statement.setObject(5, UUID.fromString("59d2457a-cb7f-45aa-bdaf-0d4f8294c3e3"));
                    statement.setObject(6, UUID.fromString("5a2a3e7c-9406-4226-a393-a5e04d56e680"));
                    statement.setDate(7, new Date(System.currentTimeMillis()));
                    statement.setObject(8, TestUtils.createUser(connection));
                    statement.setInt(9, 10);
                    statement.setString(10, "SOME_REASON".repeat(52));
                    statement.setObject(11, UUID.fromString("d93a9343-4769-4983-92df-55be734540a7"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("22001", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("value too long for type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeTaskCancelReasonId() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_task(id, address, description, customer_id, type_id, status_id, date_created, officer_id, price, cancel_reason, cancel_status_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_ADDRESS");
                    statement.setString(3, "SOME_DESCRIPTION");
                    statement.setObject(4, TestUtils.createUser(connection));
                    statement.setObject(5, UUID.fromString("59d2457a-cb7f-45aa-bdaf-0d4f8294c3e3"));
                    statement.setObject(6, UUID.fromString("5a2a3e7c-9406-4226-a393-a5e04d56e680"));
                    statement.setDate(7, new Date(System.currentTimeMillis()));
                    statement.setObject(8, TestUtils.createUser(connection));
                    statement.setInt(9, 10);
                    statement.setString(10, "SOME_REASON");
                    statement.setString(11, "SOME_REASON");
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type uuid but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeTaskReportDescription() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_task_report(id, description, time_spent, task_id, date_created) VALUES(?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_DESCRIPTION".repeat(200));
                    statement.setInt(3, 10);
                    statement.setObject(4, TestUtils.createTask(connection));
                    statement.setDate(5, new Date(System.currentTimeMillis()));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("22001", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("value too long for type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeTaskReportTimeSpent() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_task_report(id, description, time_spent, task_id, date_created) VALUES(?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_DESCRIPTION");
                    statement.setString(3, "SOME_TIME_SPENT");
                    statement.setObject(4, TestUtils.createTask(connection));
                    statement.setDate(5, new Date(System.currentTimeMillis()));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type integer but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeTaskReportTaskId() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_task_report(id, description, time_spent, task_id, date_created) VALUES(?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_DESCRIPTION");
                    statement.setInt(3, 10);
                    statement.setString(4, "SOME_TASK_ID");
                    statement.setDate(5, new Date(System.currentTimeMillis()));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type uuid but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeTaskReportDateCreated() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_task_report(id, description, time_spent, task_id, date_created) VALUES(?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_DESCRIPTION");
                    statement.setInt(3, 10);
                    statement.setObject(4, TestUtils.createTask(connection));
                    statement.setString(5, "SOME_DATE_CREATED");
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type timestamp without time zone but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeTaskStatus() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_task_status(id, task_status) VALUES(?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_STATUS".repeat(200));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("22001", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("value too long for type character varying(255)"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeTaskType() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_task_type(id, task_type) VALUES(?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_TYPE".repeat(200));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("22001", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("value too long for type character varying(255)"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeUserRole() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_user_role(id, role_name) VALUES(?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_ROLE".repeat(200));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("22001", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("value too long for type character varying(255)"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeUserEmail() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_user(id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_EMAIL".repeat(200));
                    statement.setString(3, "SOME_PASSWORD");
                    statement.setString(4, "SOME_FIRST_NAME");
                    statement.setString(5, "SOME_LAST_NAME");
                    statement.setString(6, "SOME_NICK_NAME");
                    statement.setObject(7, TestUtils.createBand(connection));
                    statement.setObject(8, UUID.fromString("9a2fe9c7-06d8-4fe0-b380-c38b1acb5e5b"));
                    statement.setObject(9, TestUtils.createTask(connection));
                    statement.setObject(10, TestUtils.createBand(connection));
                    statement.setObject(11, UUID.fromString("eb9ad14e-7f3d-407b-8cfc-d78c5552dcf0"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("22001", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("value too long for type character varying(255)"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeUserPassword() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_user(id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_EMAIL");
                    statement.setString(3, "SOME_PASSWORD".repeat(200));
                    statement.setString(4, "SOME_FIRST_NAME");
                    statement.setString(5, "SOME_LAST_NAME");
                    statement.setString(6, "SOME_NICK_NAME");
                    statement.setObject(7, TestUtils.createBand(connection));
                    statement.setObject(8, UUID.fromString("9a2fe9c7-06d8-4fe0-b380-c38b1acb5e5b"));
                    statement.setObject(9, TestUtils.createTask(connection));
                    statement.setObject(10, TestUtils.createBand(connection));
                    statement.setObject(11, UUID.fromString("eb9ad14e-7f3d-407b-8cfc-d78c5552dcf0"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("22001", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("value too long for type character varying(255)"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeUserFirstName() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_user(id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_EMAIL");
                    statement.setString(3, "SOME_PASSWORD");
                    statement.setString(4, "SOME_FIRST_NAME".repeat(200));
                    statement.setString(5, "SOME_LAST_NAME");
                    statement.setString(6, "SOME_NICK_NAME");
                    statement.setObject(7, TestUtils.createBand(connection));
                    statement.setObject(8, UUID.fromString("9a2fe9c7-06d8-4fe0-b380-c38b1acb5e5b"));
                    statement.setObject(9, TestUtils.createTask(connection));
                    statement.setObject(10, TestUtils.createBand(connection));
                    statement.setObject(11, UUID.fromString("eb9ad14e-7f3d-407b-8cfc-d78c5552dcf0"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("22001", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("value too long for type character varying(255)"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeUserLastName() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_user(id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_EMAIL");
                    statement.setString(3, "SOME_PASSWORD");
                    statement.setString(4, "SOME_FIRST_NAME");
                    statement.setString(5, "SOME_LAST_NAME".repeat(200));
                    statement.setString(6, "SOME_NICK_NAME");
                    statement.setObject(7, TestUtils.createBand(connection));
                    statement.setObject(8, UUID.fromString("9a2fe9c7-06d8-4fe0-b380-c38b1acb5e5b"));
                    statement.setObject(9, TestUtils.createTask(connection));
                    statement.setObject(10, TestUtils.createBand(connection));
                    statement.setObject(11, UUID.fromString("eb9ad14e-7f3d-407b-8cfc-d78c5552dcf0"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("22001", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("value too long for type character varying(255)"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeUserNickName() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_user(id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_EMAIL");
                    statement.setString(3, "SOME_PASSWORD");
                    statement.setString(4, "SOME_FIRST_NAME");
                    statement.setString(5, "SOME_LAST_NAME");
                    statement.setString(6, "SOME_NICK_NAME".repeat(200));
                    statement.setObject(7, TestUtils.createBand(connection));
                    statement.setObject(8, UUID.fromString("9a2fe9c7-06d8-4fe0-b380-c38b1acb5e5b"));
                    statement.setObject(9, TestUtils.createTask(connection));
                    statement.setObject(10, TestUtils.createBand(connection));
                    statement.setObject(11, UUID.fromString("eb9ad14e-7f3d-407b-8cfc-d78c5552dcf0"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("22001", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("value too long for type character varying(255)"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeUserBandId() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_user(id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_EMAIL");
                    statement.setString(3, "SOME_PASSWORD");
                    statement.setString(4, "SOME_FIRST_NAME");
                    statement.setString(5, "SOME_LAST_NAME");
                    statement.setString(6, "SOME_NICK_NAME");
                    statement.setString(7, "SOME_BAND_ID");
                    statement.setObject(8, UUID.fromString("9a2fe9c7-06d8-4fe0-b380-c38b1acb5e5b"));
                    statement.setObject(9, TestUtils.createTask(connection));
                    statement.setObject(10, TestUtils.createBand(connection));
                    statement.setObject(11, UUID.fromString("eb9ad14e-7f3d-407b-8cfc-d78c5552dcf0"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type uuid but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeUserRoleId() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_user(id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_EMAIL");
                    statement.setString(3, "SOME_PASSWORD");
                    statement.setString(4, "SOME_FIRST_NAME");
                    statement.setString(5, "SOME_LAST_NAME");
                    statement.setString(6, "SOME_NICK_NAME");
                    statement.setObject(7, TestUtils.createBand(connection));
                    statement.setString(8, "SOME_ROLE_ID");
                    statement.setObject(9, TestUtils.createTask(connection));
                    statement.setObject(10, TestUtils.createBand(connection));
                    statement.setObject(11, UUID.fromString("eb9ad14e-7f3d-407b-8cfc-d78c5552dcf0"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type uuid but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeUserTaskId() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_user(id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_EMAIL");
                    statement.setString(3, "SOME_PASSWORD");
                    statement.setString(4, "SOME_FIRST_NAME");
                    statement.setString(5, "SOME_LAST_NAME");
                    statement.setString(6, "SOME_NICK_NAME");
                    statement.setObject(7, TestUtils.createBand(connection));
                    statement.setObject(8, UUID.fromString("9a2fe9c7-06d8-4fe0-b380-c38b1acb5e5b"));
                    statement.setString(9, "SOME_TASK_ID");
                    statement.setObject(10, TestUtils.createBand(connection));
                    statement.setObject(11, UUID.fromString("eb9ad14e-7f3d-407b-8cfc-d78c5552dcf0"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type uuid but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeUserOwnBandId() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_user(id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_EMAIL");
                    statement.setString(3, "SOME_PASSWORD");
                    statement.setString(4, "SOME_FIRST_NAME");
                    statement.setString(5, "SOME_LAST_NAME");
                    statement.setString(6, "SOME_NICK_NAME");
                    statement.setObject(7, TestUtils.createBand(connection));
                    statement.setObject(8, UUID.fromString("9a2fe9c7-06d8-4fe0-b380-c38b1acb5e5b"));
                    statement.setObject(9, TestUtils.createTask(connection));
                    statement.setString(10, "SOME_OWN_BAND_ID");
                    statement.setObject(11, UUID.fromString("eb9ad14e-7f3d-407b-8cfc-d78c5552dcf0"));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type uuid but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testInvalidTypeUserRegionId() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO sd_user(id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "SOME_EMAIL");
                    statement.setString(3, "SOME_PASSWORD");
                    statement.setString(4, "SOME_FIRST_NAME");
                    statement.setString(5, "SOME_LAST_NAME");
                    statement.setString(6, "SOME_NICK_NAME");
                    statement.setObject(7, TestUtils.createBand(connection));
                    statement.setObject(8, UUID.fromString("9a2fe9c7-06d8-4fe0-b380-c38b1acb5e5b"));
                    statement.setObject(9, TestUtils.createTask(connection));
                    statement.setObject(10, TestUtils.createBand(connection));
                    statement.setString(11, "SOME_REGION_ID");
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("of type uuid but expression is of type character varying"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }
}
