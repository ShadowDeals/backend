package com.shadow.deals.user.main.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Introspected
@Data
@AllArgsConstructor
@NoArgsConstructor
@SerdeImport(FreeExecutorResponseDTO.class)
public class FreeExecutorResponseDTO implements Comparable<FreeExecutorResponseDTO> {
    @JsonProperty("executorId")
    private UUID executorId;

    @JsonProperty("executorName")
    private String executorName;

    @Override
    public int compareTo(@NotNull FreeExecutorResponseDTO o) {
        return this.executorName.compareTo(o.getExecutorName());
    }
}