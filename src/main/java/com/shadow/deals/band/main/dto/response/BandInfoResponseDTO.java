package com.shadow.deals.band.main.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shadow.deals.region.enums.RegionName;
import com.shadow.deals.user.main.dto.response.BandWorkerResponseDTO;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Introspected
@Data
@AllArgsConstructor
@NoArgsConstructor
@SerdeImport(BandInfoResponseDTO.class)
public class BandInfoResponseDTO {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("donId")
    private UUID donId;

    @JsonProperty("donName")
    private String donName;

    @JsonProperty("region")
    private RegionName region;

    @JsonProperty("workers")
    private List<BandWorkerResponseDTO> workers;
}
