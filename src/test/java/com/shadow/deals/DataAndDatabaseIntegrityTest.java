package com.shadow.deals;

import java.sql.Connection;
import java.sql.DriverManager;
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
public class DataAndDatabaseIntegrityTest extends BaseTestContainerTest {

    @Test
    void testInvalidPrimaryKey() {
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
    void testNonUnique() {
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

    @Test
    void testInvalidForeignKey() {
        String invalidRoleId = "1704041f-54ea-46c9-8c22-11bf60fefb73";

        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {

                String sql = "INSERT INTO sd_user(id, email, first_name, last_name, role_id) VALUES(?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, UUID.randomUUID());
                    statement.setString(2, "test@example.com");
                    statement.setString(3, "Test");
                    statement.setString(4, "User");
                    statement.setObject(5, UUID.fromString(invalidRoleId));
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testTransactionInsertFailed() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {
                connection.setAutoCommit(false);
                try {
                    Statement stmt = connection.createStatement();

                    // Вставка в транзакции
                    stmt.executeUpdate(
                        "INSERT INTO sd_user_role(id, role_name) VALUES('1704041f-54ea-46c9-8c22-11bf60fefb73', 'TEST_ROLE')");

                    // Намеренная ошибка - нарушение foreign key
                    stmt.executeUpdate(
                        "INSERT INTO sd_user(id, email, first_name, last_name, role_id) VALUES('fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', 'test@email.ru', 'KIRILL', 'SIMOVIN', '1704041f-54ea-46c9-8c22-11bf60fefb74')"
                    );

                    connection.commit();
                } catch (SQLException e) {
                    connection.rollback();

                    // Проверяем, что данные откатились
                    Statement checkStmt = connection.createStatement();

                    Assertions.assertEquals(0, getSelectResult(
                            checkStmt,
                            "SELECT COUNT(*) FROM sd_user_role WHERE id = '1704041f-54ea-46c9-8c22-11bf60fefb73'"
                        )
                    );

                    Assertions.assertEquals(0, getSelectResult(
                            checkStmt,
                            "SELECT COUNT(*) FROM sd_user WHERE id = 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0'"
                        )
                    );

                    throw e;
                }
            }
        });

        Assertions.assertEquals("23503", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("violates foreign key constraint"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    @Test
    void testTransactionInsertSuccess() {
        Assertions.assertDoesNotThrow(() -> {
            try (Connection connection = getConnection()) {
                connection.setAutoCommit(false);
                Statement stmt = connection.createStatement();

                // Вставка в транзакции
                stmt.executeUpdate("INSERT INTO sd_user_role(id, role_name) VALUES('1704041f-54ea-46c9-8c22-11bf60fefb73', 'TEST_ROLE')");

                // Намеренная ошибка - нарушение foreign key
                stmt.executeUpdate(
                    "INSERT INTO sd_user(id, email, first_name, last_name, role_id) VALUES('fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', 'test@email.ru', 'KIRILL', 'SIMOVIN', '1704041f-54ea-46c9-8c22-11bf60fefb73')"
                );

                connection.commit();

                Statement checkStmt = connection.createStatement();

                Assertions.assertEquals(1, getSelectResult(
                        checkStmt,
                        "SELECT COUNT(*) FROM sd_user_role WHERE id = '1704041f-54ea-46c9-8c22-11bf60fefb73'"
                    )
                );

                Assertions.assertEquals(1, getSelectResult(
                        checkStmt,
                        "SELECT COUNT(*) FROM sd_user WHERE id = 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0'"
                    )
                );
            }
        });
    }

    @Test
    void testTransactionDeleteSuccess() {
        Assertions.assertDoesNotThrow(() -> {
            try (Connection connection = getConnection()) {
                connection.setAutoCommit(false);
                Statement stmt = connection.createStatement();

                // Вставка в транзакции
                stmt.executeUpdate("INSERT INTO sd_user_role(id, role_name) VALUES('1704041f-54ea-46c9-8c22-11bf60fefb73', 'TEST_ROLE')");

                // Намеренная ошибка - нарушение foreign key
                stmt.executeUpdate(
                    "INSERT INTO sd_user(id, email, first_name, last_name, role_id) VALUES('fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', 'test@email.ru', 'KIRILL', 'SIMOVIN', '1704041f-54ea-46c9-8c22-11bf60fefb73')"
                );

                connection.commit();

                Statement checkStmt = connection.createStatement();

                Assertions.assertEquals(1, getSelectResult(
                        checkStmt,
                        "SELECT COUNT(*) FROM sd_user_role WHERE id = '1704041f-54ea-46c9-8c22-11bf60fefb73'"
                    )
                );

                Assertions.assertEquals(1, getSelectResult(
                        checkStmt,
                        "SELECT COUNT(*) FROM sd_user WHERE id = 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0'"
                    )
                );

                stmt.executeUpdate(
                    "DELETE FROM sd_user_role WHERE id = '1704041f-54ea-46c9-8c22-11bf60fefb73'"
                );

                Assertions.assertEquals(0, getSelectResult(
                        checkStmt,
                        "SELECT COUNT(*) FROM sd_user_role WHERE id = '1704041f-54ea-46c9-8c22-11bf60fefb73'"
                    )
                );

                Assertions.assertEquals(0, getSelectResult(
                        checkStmt,
                        "SELECT COUNT(*) FROM sd_user WHERE id = 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0'"
                    )
                );
            }
        });
    }

    @Test
    void testTransactionInsertIncorrectType() {
        SQLException exception = Assertions.assertThrows(SQLException.class, () -> {
            try (Connection connection = getConnection()) {

                String sql = "INSERT INTO sd_user_role(id, role_name) VALUES(?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, 1);
                    statement.setString(2, "ROLE_NAME");
                    statement.execute();
                }
            }
        });

        Assertions.assertEquals("42804", exception.getSQLState());
        Assertions.assertTrue(exception.getMessage().contains("column \"id\" is of type uuid but expression is of type integer"));
        Assertions.assertEquals("org.postgresql.util.PSQLException", exception.getClass().getName());
    }

    private int getSelectResult(Statement stmt, String query) throws SQLException {
        if (stmt == null) {
            return -1;
        }

        ResultSet rs = stmt.executeQuery(query);
        rs.next();

        return rs.getInt(1);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            postgreSQLContainer.getJdbcUrl(),
            postgreSQLContainer.getUsername(),
            postgreSQLContainer.getPassword()
        );
    }
}
