package com.shadow.deals.email.config;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.email.javamail.sender.MailPropertiesProvider;
import io.micronaut.email.javamail.sender.SessionProvider;
import jakarta.inject.Singleton;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import lombok.RequiredArgsConstructor;

/**
 * This class allows to configure a session for authentication in the mail service.
 *
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class MailSession implements SessionProvider {
    /**
     * Username for authentication in the mail service.
     */
    private final String username;

    /**
     * Password for authentication in the mail service.
     */
    private final String password;

    /**
     * Instance of the interface that allows to get mail properties.
     */
    private final MailPropertiesProvider mailPropertiesProvider;

    /**
     * This method allows to configure a session for authentication in the mail service.
     *
     * @return Configured session.
     */
    @Override
    @NonNull
    public Session session() {
        return Session.getInstance(mailPropertiesProvider.mailProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
}
