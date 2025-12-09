package com.shadow.deals;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import java.util.Map;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * @author Kirill "Tamada" Simovin
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MicronautTest
public abstract class BaseTestContainerTest implements TestPropertyProvider {

    protected static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:latest"))
        .withDatabaseName("shadow")
        .withUsername("postgres")
        .withPassword("postgrespass")
        .withReuse(true)
        .withExposedPorts(5432);

    static {
        postgreSQLContainer.start();
    }

    @Override
    public @NonNull Map<String, String> getProperties() {
        return Map.of(
            "datasources.default.url", postgreSQLContainer.getJdbcUrl(),
            "datasources.default.username", postgreSQLContainer.getUsername(),
            "datasources.default.password", postgreSQLContainer.getPassword(),
            "datasources.default.driver-class-name", "org.postgresql.Driver"
        );
    }
}
