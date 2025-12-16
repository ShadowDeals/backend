package com.shadow.deals.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
public class TestUtils {

    public static UUID createTask(Connection connection) throws SQLException {

        return createTask(
            connection,
            createUser(connection),
            UUID.fromString("afa1f1b4-6758-4a0a-a862-570c7fddd3a0"),
            UUID.fromString("5953308d-d630-4310-8689-023033d4281c"),
            null,
            null
        );
    }

    public static UUID createTask(Connection connection, UUID customerId, UUID typeId, UUID statusId, UUID cancelStatusId, UUID officerId)
        throws SQLException {
        if (connection == null) {
            return null;
        }

        String sql = "INSERT INTO sd_task(id, customer_id, type_id, status_id, cancel_status_id, officer_id) VALUES(?, ?, ?, ?)";

        UUID id = UUID.randomUUID();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.setObject(2, customerId);
            statement.setObject(3, typeId);
            statement.setObject(4, statusId);
            statement.setObject(4, cancelStatusId);
            statement.setObject(4, officerId);
            statement.execute();
        }

        return id;
    }

    public static UUID createUser(Connection connection) throws SQLException {
        if (connection == null) {
            return null;
        }

        String sql = "INSERT INTO sd_user(id, email, first_name, last_name, role_id) VALUES(?, ?, ?, ?, ?)";

        UUID userId = UUID.randomUUID();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, userId);
            statement.setString(2, "test@example.com");
            statement.setString(3, "Test");
            statement.setString(4, "User");
            statement.setObject(5, UUID.fromString("fc55ec84-5c0a-4890-9752-cba4c5fa6fa0"));
            statement.execute();
        }

        return userId;
    }

    public static UUID createBand(Connection connection, UUID regionId) throws SQLException {
        if (connection == null) {
            return null;
        }

        String sql = "INSERT INTO sd_band(id, region_id) VALUES(?, ?)";

        UUID bandId = UUID.randomUUID();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, bandId);
            statement.setObject(2, regionId);
            statement.execute();
        }

        return bandId;
    }

    public static void createBandRequest(Connection connection, UUID bandId, UUID userId) throws SQLException {
        if (connection == null) {
            return;
        }

        String sql = "insert into sd_band_request (id, user_id, band_id) values (?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, UUID.randomUUID());
            statement.setObject(2, userId);
            statement.setObject(3, bandId);
            statement.execute();
        }
    }

    public static void createBandTask(Connection connection, UUID bandId, UUID taskId) throws SQLException {
        if (connection == null) {
            return;
        }

        String sql = "insert into sd_band_task (id, band_id, task_id) values (?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, UUID.randomUUID());
            statement.setObject(2, bandId);
            statement.setObject(3, taskId);
            statement.execute();
        }
    }

    public static void createBlockedBand(Connection connection, UUID bandId) throws SQLException {
        if (connection == null) {
            return;
        }

        String sql = "insert into sd_blocked_band (id, band_id) values (?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, UUID.randomUUID());
            statement.setObject(2, bandId);
            statement.execute();
        }
    }

    public static void createTaskReport(Connection connection, UUID taskId) throws SQLException {
        if (connection == null) {
            return;
        }

        String sql = "INSERT INTO sd_task_report(id, task_id) VALUES(?, ?)";

        UUID reporterId = UUID.randomUUID();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, reporterId);
            statement.setObject(2, taskId);
            statement.execute();
        }
    }
}
