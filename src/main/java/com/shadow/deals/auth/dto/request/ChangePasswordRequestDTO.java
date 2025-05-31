package com.shadow.deals.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * This class describes the client's request to change the password.
 *
 * @author Kirill "Tamada" Simovin
 */
@Introspected
@Data
@AllArgsConstructor
@NoArgsConstructor
@SerdeImport(ChangePasswordRequestDTO.class)
public class ChangePasswordRequestDTO {
    /**
     * The user's email.
     */
    @Email
    @NotBlank
    @JsonProperty("email")
    private String email;

    /**
     * The user's new password.
     */
    @NotBlank
    @JsonProperty("newPassword")
    private String newPassword;

    /**
     * The code that is generated when sending a message to the user. Used for user validation.
     */
    @NotBlank
    @JsonProperty("changePasswordCode")
    private UUID changePasswordCode;
}
