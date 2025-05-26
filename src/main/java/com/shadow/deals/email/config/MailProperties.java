package com.shadow.deals.email.config;

import io.micronaut.context.annotation.Value;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.email.javamail.sender.MailPropertiesProvider;
import jakarta.inject.Singleton;

import java.util.Properties;
import java.util.Set;

/**
 * This class allows to configure a properties for using the mail service.
 *
 * @author Kirill "Tamada" Simovin
 */
@Singleton
public class MailProperties implements MailPropertiesProvider {
    /**
     * Is debug needed when working with the email service?
     */
    @Value("${kast.mail.debug}")
    private boolean mailDebug;

    /**
     * The email service host.
     */
    @Value("${kast.mail.host}")
    private String mailHost;

    /**
     * The email service port.
     */
    @Value("${kast.mail.port}")
    private int mailPort;

    /**
     * The protocol for transmitting emails.
     */
    @Value("${kast.mail.transport.protocol}")
    private String mailTransportProtocol;

    /**
     * Is authentication required on the SMTP server.
     */
    @Value("${kast.mail.smtp.auth}")
    private boolean mailSmtpAuth;

    /**
     * Specifies the use of the STARTTLS command when authenticating on the SMTP server.
     *
     * @see <a href="https://en.wikipedia.org/wiki/Opportunistic_TLS">What is STARTTLS</a>.
     */
    @Value("${kast.mail.smtp.starttls.enable}")
    private boolean mailSmtpStarttlsEnable;

    /**
     * Is check the server identity as specified by RFC 2595 needed. These additional checks based on the content of the
     * server's certificate are intended to prevent man-in-the-middle attacks.
     */
    @Value("${kast.mail.smtp.ssl.checkserveridentity}")
    private boolean mailSmtpSslCheckServerIdentity;

    /**
     * A set of hosts that are trusted.
     */
    @Value("${kast.mail.smtp.ssl.trust}")
    private Set<String> mailSmtpSslTrust;

    /**
     * Is it necessary to use the SSL protocol and SSL port (by default).
     */
    @Value("${kast.mail.smtp.ssl.enable}")
    private boolean mailSmtpSslEnable;

    /**
     * This method allows to configure a properties for using the mail service.
     *
     * @return Configured {@link Properties} for using the mail service.
     */
    @Override
    @NonNull
    public Properties mailProperties() {
        Properties properties = new Properties();
        properties.put("mail.debug", mailDebug);
        properties.put("mail.host", mailHost);
        properties.put("mail.port", mailPort);
        properties.put("mail.transport.protocol", mailTransportProtocol);
        properties.put("mail.smtp.auth", mailSmtpAuth);
        properties.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
        properties.put("mail.smtp.ssl.checkserveridentity", mailSmtpSslCheckServerIdentity);
        properties.put("mail.smtp.ssl.trust", String.join(" ", mailSmtpSslTrust));
        properties.put("mail.smtp.ssl.enable", mailSmtpSslEnable);
        return properties;
    }
}
