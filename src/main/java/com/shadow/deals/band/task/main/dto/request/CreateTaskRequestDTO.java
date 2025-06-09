package com.shadow.deals.band.task.main.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shadow.deals.band.task.type.enums.TaskTypeEnum;
import com.shadow.deals.region.enums.RegionName;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kirill "Tamada" Simovin
 */
@Introspected
@Data
@AllArgsConstructor
@NoArgsConstructor
@SerdeImport(CreateTaskRequestDTO.class)
public class CreateTaskRequestDTO {
    @Max(510)
    @JsonProperty("address")
    private String address;

    @Max(1024)
    @JsonProperty("description")
    private String description;

    @JsonProperty("taskType")
    private TaskTypeEnum taskType;

    @JsonProperty("price")
    private int price;

    @JsonProperty("region")
    private RegionName region;
}