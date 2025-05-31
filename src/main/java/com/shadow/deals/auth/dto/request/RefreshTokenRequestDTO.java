package com.shadow.deals.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class describes the client's request to refresh the access token.
 *
 * @author Kirill "Tamada" Simovin
 */
@Introspected
@Data
@AllArgsConstructor
@NoArgsConstructor
@SerdeImport(RefreshTokenRequestDTO.class)
public class RefreshTokenRequestDTO {
    /**
     * Refresh token value.
     */
    @NotBlank
    @JsonProperty("refreshToken")
    private String refreshToken;
}