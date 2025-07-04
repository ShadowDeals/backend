package com.shadow.deals.band.task.report.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shadow.deals.band.task.status.enums.TaskStatusEnum;
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
@NoArgsConstructor
@SerdeImport(TaskReportRequestDTO.class)
public class TaskReportRequestDTO {
    @JsonProperty("status")
    private TaskStatusEnum status;

    @JsonProperty("description")
    private String description;

    @JsonProperty("timeSpent")
    private int timeSpent;
}