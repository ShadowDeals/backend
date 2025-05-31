package com.shadow.deals.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shadow.deals.user.region.enums.RegionName;
import com.shadow.deals.user.role.enums.UserRoleName;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class describes the client's sign up request.
 *
 * @author Kirill "Tamada" Simovin
 */
@Introspected
@Data
@AllArgsConstructor
@NoArgsConstructor
@SerdeImport(SignUpRequestDTO.class)
public class SignUpRequestDTO {
    /**
     * User's nickname.
     */
    @NotBlank
    @JsonProperty("nickname")
    private String nickname;

    /**
     * User's first name.
     */
    @NotBlank
    @JsonProperty("firstName")
    private String firstName;

    /**
     * User's last name.
     */
    @NotBlank
    @JsonProperty("lastName")
    private String lastName;

    /**
     * User's password.
     */
    @NotBlank
    @JsonProperty("password")
    private String password;

    /**
     * User's email.
     */
    @Email
    @NotBlank
    @JsonProperty("email")
    private String email;

    @NotBlank
    @JsonProperty("role")
    private UserRoleName role;

    @JsonProperty("region")
    private RegionName region;
}