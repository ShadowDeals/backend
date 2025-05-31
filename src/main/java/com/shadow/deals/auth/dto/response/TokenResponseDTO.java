package com.shadow.deals.auth.dto.response;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This class describes the server's response when logging in and refreshing the access token.
 *
 * @author Kirill "Tamada" Simovin
 */
@Getter
@Setter
@ToString
@Serdeable
@AllArgsConstructor
public class TokenResponseDTO {
    /**
     * User's access token.
     */
    private String accessToken;

    /**
     * The lifetime of the access token in seconds.
     */
    private int accessExpiresAt;

    /**
     * User's refresh token.
     */
    private String refreshToken;

    /**
     * User's email.
     */
    private String email;
}