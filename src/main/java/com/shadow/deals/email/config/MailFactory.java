package com.shadow.deals.email.config;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import io.micronaut.email.javamail.sender.MailPropertiesProvider;
import io.micronaut.email.javamail.sender.SessionProvider;
import jakarta.inject.Singleton;

/**
 * This class allows to define the implementation of beans associated with the email sending service.
 *
 * @author Kirill "Tamada" Simovin
 */
@Factory
public class MailFactory {
    /**
     * Username for authentication in the mail service.
     */
    @Value("${shadow-deals.mail.username}")
    private String username;

    /**
     * Password for authentication in the mail service.
     */
    @Value("${shadow-deals.mail.password}")
    private String password;

    /**
     * This method allows to define the {@link MailPropertiesProvider} bean as {@link MailProperties}.
     *
     * @return Configured {@link MailPropertiesProvider} bean.
     */
    @Singleton
    public MailPropertiesProvider mailPropertiesProvider() {
        return new MailProperties();
    }

    /**
     * This method allows to define the {@link SessionProvider} bean as {@link MailSession}.
     *
     * @return Configured {@link SessionProvider} bean.
     */
    @Singleton
    public SessionProvider sessionProvider(MailProperties mailPropertiesProvider) {
        return new MailSession(username, password, mailPropertiesProvider);
    }
}
