package com.shadow.deals.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Kirill "Tamada" Simovin
 */
public class TestUtils {

    public static UUID createTask(Connection connection, UUID customerId) throws SQLException {
        return createTask(
            connection,
            customerId,
            UUID.fromString("afa1f1b4-6758-4a0a-a862-570c7fddd3a0"),
            UUID.fromString("1a653609-da64-460b-bc17-57690d1f00aa"),
            UUID.fromString("1a653609-da64-460b-bc17-57690d1f00aa"),
            customerId
        );
    }

    public static void createTaskByStatus(Connection connection, UUID statusId) throws SQLException {
        createTask(
            connection,
            createUser(connection),
            UUID.fromString("afa1f1b4-6758-4a0a-a862-570c7fddd3a0"),
            statusId,
            statusId,
            null
        );
    }

    public static void createTaskByType(Connection connection, UUID typeId) throws SQLException {
        createTask(
            connection,
            createUser(connection),
            typeId,
            UUID.fromString("1a653609-da64-460b-bc17-57690d1f00aa"),
            UUID.fromString("1a653609-da64-460b-bc17-57690d1f00aa"),
            null
        );
    }

    public static UUID createTask(Connection connection) throws SQLException {

        return createTask(
            connection,
            createUser(connection),
            UUID.fromString("afa1f1b4-6758-4a0a-a862-570c7fddd3a0"),
            UUID.fromString("1a653609-da64-460b-bc17-57690d1f00aa"),
            UUID.fromString("1a653609-da64-460b-bc17-57690d1f00aa"),
            null
        );
    }

    public static UUID createTask(Connection connection, UUID customerId, UUID typeId, UUID statusId, UUID cancelStatusId, UUID officerId)
        throws SQLException {
        if (connection == null) {
            return null;
        }

        String sql = "INSERT INTO sd_task(id, customer_id, type_id, status_id, cancel_status_id, officer_id) VALUES(?, ?, ?, ?, ?, ?)";

        UUID id = UUID.randomUUID();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.setObject(2, customerId);
            statement.setObject(3, typeId);
            statement.setObject(4, statusId);
            statement.setObject(5, cancelStatusId);
            statement.setObject(6, officerId);
            statement.execute();
        }

        return id;
    }

    public static UUID createUser(Connection connection) throws SQLException {
        return createUser(connection, UUID.fromString("170f5f8f-bf1b-4d1b-ab21-7714a83880f1"));
    }

    public static UUID createUser(Connection connection, UUID regionId) throws SQLException {
        if (connection == null) {
            return null;
        }

        String email = "test@example.com";

        Statement selectStatement = connection.createStatement();
        ResultSet rs = selectStatement.executeQuery("SELECT * FROM sd_user WHERE email = '" + email + "'");
        if (rs.next()) {
            return UUID.fromString(rs.getString("id"));
        }

        String sql = "INSERT INTO sd_user(id, email, first_name, last_name, role_id, region_id) VALUES(?, ?, ?, ?, ?, ?)";

        UUID userId = UUID.randomUUID();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, userId);
            statement.setString(2, email);
            statement.setString(3, "Test");
            statement.setString(4, "User");
            statement.setObject(5, UUID.fromString("fc55ec84-5c0a-4890-9752-cba4c5fa6fa0"));
            statement.setObject(6, regionId);
            statement.execute();
        }

        return userId;
    }

    public static UUID createBand(Connection connection) throws SQLException {
        return createBand(connection, UUID.fromString("170f5f8f-bf1b-4d1b-ab21-7714a83880f1"));
    }

    public static UUID createBand(Connection connection, UUID regionId) throws SQLException {
        if (connection == null) {
            return null;
        }

        Statement selectStatement = connection.createStatement();
        ResultSet rs = selectStatement.executeQuery("SELECT * FROM sd_band WHERE region_id = '" + regionId + "'");
        if (rs.next()) {
            return UUID.fromString(rs.getString("id"));
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

    public static void createActivationCode(Connection connection, UUID userId) throws SQLException {
        createActivationCode(connection, userId, UUID.randomUUID().toString());
    }

    public static UUID createActivationCode(Connection connection, UUID userId, String code) throws SQLException {
        if (connection == null) {
            return null;
        }

        String sql = "INSERT INTO sd_activation_code(id, activation_code, is_activated, sd_user_id) VALUES(?, ?, ?, ?)";

        UUID id = UUID.randomUUID();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.setString(2, code);
            statement.setBoolean(3, false);
            statement.setObject(4, userId);
            statement.execute();
        }

        return id;
    }

    public static UUID createRefreshToken(Connection connection) throws SQLException {
        return createRefreshToken(connection, createUser(connection));
    }

    public static UUID createRefreshToken(Connection connection, UUID userId) throws SQLException {
        if (connection == null) {
            return null;
        }

        String sql = "insert into sd_refresh_token (id, refresh_token, user_id) values (?, ?, ?);";

        UUID id = UUID.randomUUID();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.setString(2, "SOME_REFRESH_TOKEN");
            statement.setObject(3, userId);
            statement.execute();
        }

        return id;
    }

    public static UUID getBandId(String accessToken) {
        String bandId = getFromAccessToken(accessToken, "bandId");
        if (bandId == null) {
            return null;
        }
        return UUID.fromString(bandId);
    }

    public static String getFromAccessToken(String accessToken, String key) {
        if (accessToken == null) {
            return null;
        }
        String[] chunks = accessToken.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(chunks[1]));

        try {
            return new ObjectMapper().readValue(payload, HashMap.class).get(key).toString();
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
