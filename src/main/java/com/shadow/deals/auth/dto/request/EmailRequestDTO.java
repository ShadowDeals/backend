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
 * This class describes the client's request to send the email in body.
 *
 * @author Kirill "Tamada" Simovin
 */
@Introspected
@Data
@AllArgsConstructor
@NoArgsConstructor
@SerdeImport(EmailRequestDTO.class)
public class EmailRequestDTO {
    /**
     * The user's email address.
     */
    @Email
    @NotBlank
    @JsonProperty("email")
    private String email;
}