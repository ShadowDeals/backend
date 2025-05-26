package com.shadow.deals.auth.password;

import jakarta.inject.Singleton;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * This class implements password encoding using BCrypt.
 *
 * @author Kirill "Tamada" Simovin
 */
@Singleton
public class BCryptPasswordEncoderService implements PasswordEncoder {
    /**
     * Instance of interface, that allows encoding passwords.
     */
    private final org.springframework.security.crypto.password.PasswordEncoder delegate = new BCryptPasswordEncoder();

    /**
     * This method allows to encode the raw password.
     *
     * @param rawPassword the password that needs to be encoded.
     * @return Encoded password.
     */
    @Override
    public String encode(String rawPassword) {
        return delegate.encode(rawPassword);
    }

    /**
     * This method allows to check is raw password match encoded password.
     *
     * @param rawPassword     the raw password to encode and match.
     * @param encodedPassword the encoded password from storage to compare with.
     * @return {@code true} if the raw password, after encoding, matches the encoded password from storage. Otherwise,
     * returns {@code false}.
     */
    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return delegate.matches(rawPassword, encodedPassword);
    }
}