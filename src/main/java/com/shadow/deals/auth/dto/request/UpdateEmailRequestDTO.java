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
 * This class describes the client's request to update the user's email.
 *
 * @author Kirill "Tamada" Simovin
 */
@Introspected
@Data
@AllArgsConstructor
@NoArgsConstructor
@SerdeImport(UpdateEmailRequestDTO.class)
public class UpdateEmailRequestDTO {
    /**
     * The user's old email address.
     */
    @Email
    @NotBlank
    @JsonProperty("oldEmail")
    private String oldEmail;

    /**
     * The user's password.
     */
    @NotBlank
    @JsonProperty("password")
    private String password;

    /**
     * The user's new email address.
     */
    @Email
    @NotBlank
    @JsonProperty("newEmail")
    private String newEmail;
}