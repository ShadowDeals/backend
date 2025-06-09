package com.shadow.deals.band.task.main.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Introspected
@Data
@AllArgsConstructor
@SerdeImport(TaskExecutorResponseDTO.class)
public class TaskExecutorResponseDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private UUID id;
}
