package com.shadow.deals.band.request.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shadow.deals.user.role.enums.UserRoleName;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Introspected
@Data
@AllArgsConstructor
@NoArgsConstructor
@SerdeImport(RequestResponseDTO.class)
public class RequestResponseDTO implements Comparable<RequestResponseDTO> {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("userRole")
    private UserRoleName userRole;

    @JsonProperty("dateCreated")
    private Instant dateCreated;

    @Override
    public int compareTo(@NotNull RequestResponseDTO o) {
        return dateCreated.compareTo(o.getDateCreated());
    }
}
