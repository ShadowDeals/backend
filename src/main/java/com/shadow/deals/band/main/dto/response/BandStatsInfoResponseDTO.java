package com.shadow.deals.band.main.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kirill "Tamada" Simovin
 */
@Introspected
@Data
@AllArgsConstructor
@SerdeImport(BandStatsInfoResponseDTO.class)
public class BandStatsInfoResponseDTO {
    @JsonProperty("adminsCount")
    private long adminsCount;

    @JsonProperty("soldiersCount")
    private long soldiersCount;

    @JsonProperty("tasksCount")
    private int tasksCount;

    @JsonProperty("completedTasksCount")
    private int completedTasksCount;

    @JsonProperty("totalPrice")
    private int totalPrice;
}