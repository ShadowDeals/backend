package com.shadow.deals.auth.password;

/**
 * Service interface for encoding passwords.
 *
 * @author Kirill "Tamada" Simovin
 */
public interface PasswordEncoder {
    /**
     * This signature describes the method, that allows to encode the raw password.
     *
     * @param rawPassword the password that needs to be encoded.
     * @return Encoded password.
     */
    String encode(String rawPassword);

    /**
     * This signature describes the method, that allows to check is raw password match encoded password.
     *
     * @param rawPassword     the raw password to encode and match.
     * @param encodedPassword the encoded password from storage to compare with.
     * @return {@code true} if the raw password, after encoding, matches the encoded password from storage. Otherwise,
     * returns {@code false}.
     */
    boolean matches(String rawPassword, String encodedPassword);
}