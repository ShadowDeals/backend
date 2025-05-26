package com.shadow.deals.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("access_token")
    private String accessToken;

    /**
     * The lifetime of the access token in seconds.
     */
    @JsonProperty("access_expires_at")
    private int accessExpiresAt;

    /**
     * User's refresh token.
     */
    @JsonProperty("refresh_token")
    private String refreshToken;

    /**
     * User's email.
     */
    @JsonProperty("email")
    private String email;
}