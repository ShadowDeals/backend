package com.shadow.deals.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class describes the client's sign in request.
 *
 * @author Kirill "Tamada" Simovin
 */
@Introspected
@Data
@AllArgsConstructor
@NoArgsConstructor
@SerdeImport(SignInRequestDTO.class)
public class SignInRequestDTO {
    /**
     * User's email.
     */
    @Email
    @NotBlank
    @JsonProperty("email")
    private String email;

    /**
     * User's password.
     */
    @NotBlank
    @JsonProperty("password")
    private String password;

    /**
     * Whether to remember the user for a long time after logging in.
     */
    @NotBlank
    @JsonProperty("is_remember_me")
    private boolean isRememberMe;
}