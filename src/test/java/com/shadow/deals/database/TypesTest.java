package com.shadow.deals.database;

import com.shadow.deals.base.BaseTestContainerTest;
import java.sql.Connection;
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
    void testInvalidType() {
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
}
