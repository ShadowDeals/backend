package com.shadow.deals.database;

import com.shadow.deals.BaseTestContainerTest;
import com.shadow.deals.util.TestUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Kirill "Tamada" Simovin
 */
public class CascadeDeleteTest extends BaseTestContainerTest {

    @Test
    void testCascadeDeleteBand() throws SQLException {
        try (Connection connection = getConnection()) {
            UUID bandId = TestUtils.createBand(connection);
            UUID userId = TestUtils.createUser(connection);

            TestUtils.createBandRequest(connection, bandId, userId);
            TestUtils.createBandTask(connection, bandId, TestUtils.createTask(connection, userId));
            TestUtils.createBlockedBand(connection, bandId);

            String sql = "DELETE FROM sd_band WHERE id = '" + bandId + "'";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
            }

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM sd_band_request WHERE band_id = '" + bandId + "'");
            Assertions.assertFalse(rs.next());

            rs = statement.executeQuery("SELECT * FROM sd_blocked_band WHERE band_id = '" + bandId + "'");
            Assertions.assertFalse(rs.next());

            rs = statement.executeQuery("SELECT * FROM sd_band_task WHERE band_id = '" + bandId + "'");
            Assertions.assertFalse(rs.next());
        }
    }

    @Test
    void testCascadeDeleteRegion() throws SQLException {
        try (Connection connection = getConnection()) {
            TestUtils.createBand(connection);
            TestUtils.createUser(connection);

            String sql = "DELETE FROM sd_region WHERE id = '170f5f8f-bf1b-4d1b-ab21-7714a83880f1'";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
            }

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM sd_band WHERE region_id = '170f5f8f-bf1b-4d1b-ab21-7714a83880f1'");
            Assertions.assertFalse(rs.next());

            rs = statement.executeQuery("SELECT * FROM sd_user WHERE region_id = '170f5f8f-bf1b-4d1b-ab21-7714a83880f1'");
            Assertions.assertFalse(rs.next());
        }
    }

    @Test
    void testCascadeDeleteTask() throws SQLException {
        try (Connection connection = getConnection()) {
            UUID taskId = TestUtils.createTask(connection);

            TestUtils.createBandTask(
                connection,
                TestUtils.createBand(connection),
                taskId
            );

            TestUtils.createTaskReport(connection, taskId);

            String sql = "DELETE FROM sd_task WHERE id = '" + taskId + "'";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
            }

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM sd_task_report WHERE task_id = '" + taskId + "'");
            Assertions.assertFalse(rs.next());

            rs = statement.executeQuery("SELECT * FROM sd_band_task WHERE task_id = '" + taskId + "'");
            Assertions.assertFalse(rs.next());
        }
    }

    @Test
    void testCascadeDeleteTaskStatus() throws SQLException {
        try (Connection connection = getConnection()) {
            TestUtils.createTask(connection);

            String sql = "DELETE FROM sd_task_status WHERE id = '1a653609-da64-460b-bc17-57690d1f00aa'";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
            }

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM sd_task WHERE status_id = '1a653609-da64-460b-bc17-57690d1f00aa'");
            Assertions.assertFalse(rs.next());

            rs = statement.executeQuery("SELECT * FROM sd_task WHERE cancel_status_id = '1a653609-da64-460b-bc17-57690d1f00aa'");
            Assertions.assertFalse(rs.next());
        }
    }

    @Test
    void testCascadeDeleteTaskType() throws SQLException {
        try (Connection connection = getConnection()) {
            TestUtils.createTask(connection);

            String sql = "DELETE FROM sd_task_type WHERE id = 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0'";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
            }

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM sd_task WHERE type_id = 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0'");
            Assertions.assertFalse(rs.next());
        }
    }

    @Test
    void testCascadeDeleteUser() throws SQLException {
        try (Connection connection = getConnection()) {
            UUID userId = TestUtils.createUser(connection);

            TestUtils.createTask(connection, userId);

            UUID bandId = TestUtils.createBand(connection);
            TestUtils.createBandRequest(connection, bandId, userId);
            TestUtils.createActivationCode(connection, userId);
            TestUtils.createRefreshToken(connection, userId);

            String sql = "DELETE FROM sd_user WHERE id = '" + userId + "'";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
            }

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM sd_task WHERE officer_id = '" + userId + "'");
            Assertions.assertFalse(rs.next());

            rs = statement.executeQuery("SELECT * FROM sd_task WHERE customer_id = '" + userId + "'");
            Assertions.assertFalse(rs.next());

            rs = statement.executeQuery("SELECT * FROM sd_band_request WHERE user_id = '" + userId + "'");
            Assertions.assertFalse(rs.next());

            rs = statement.executeQuery("SELECT * FROM sd_activation_code WHERE sd_user_id = '" + userId + "'");
            Assertions.assertFalse(rs.next());

            rs = statement.executeQuery("SELECT * FROM sd_refresh_token WHERE user_id = '" + userId + "'");
            Assertions.assertFalse(rs.next());
        }
    }

    @Test
    void testCascadeDeleteUserRole() throws SQLException {
        try (Connection connection = getConnection()) {
            TestUtils.createUser(connection);

            String sql = "DELETE FROM sd_user_role WHERE id = 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0'";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
            }

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM sd_user WHERE role_id = 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0'");
            Assertions.assertFalse(rs.next());
        }
    }
}
