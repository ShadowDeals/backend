package com.shadow.deals.user.main.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shadow.deals.user.role.enums.UserRoleName;
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
@SerdeImport(BandWorkerResponseDTO.class)
public class BandWorkerResponseDTO implements Comparable<BandWorkerResponseDTO> {
    @JsonProperty("workerId")
    private UUID workerId;

    @JsonProperty("workerName")
    private String workerName;

    @JsonProperty("role")
    private UserRoleName role;

    @Override
    public int compareTo(@NotNull BandWorkerResponseDTO o) {
        return workerName.compareTo(o.getWorkerName());
    }
}
