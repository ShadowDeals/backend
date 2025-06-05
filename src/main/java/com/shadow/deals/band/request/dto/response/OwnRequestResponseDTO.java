package com.shadow.deals.band.request.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shadow.deals.region.enums.RegionName;
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
@SerdeImport(OwnRequestResponseDTO.class)
public class OwnRequestResponseDTO implements Comparable<OwnRequestResponseDTO> {
    @JsonProperty("bandId")
    private UUID bandId;

    @JsonProperty("bandRegion")
    private RegionName bandRegion;

    @JsonProperty("dateCreated")
    private Instant dateCreated;

    @Override
    public int compareTo(@NotNull OwnRequestResponseDTO o) {
        return dateCreated.compareTo(o.getDateCreated());
    }
}
