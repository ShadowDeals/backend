package com.shadow.deals.band.task.report.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shadow.deals.band.task.status.enums.TaskStatusEnum;
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
@SerdeImport(TaskReportResponseDTO.class)
public class TaskReportResponseDTO implements Comparable<TaskReportResponseDTO> {
    @JsonProperty("reportId")
    private UUID reportId;

    @JsonProperty("taskStatus")
    private TaskStatusEnum taskStatus;

    @JsonProperty("taskId")
    private UUID taskId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("timeSpent")
    private int timeSpent;

    @JsonIgnore
    private Instant dateCreated;

    @Override
    public int compareTo(@NotNull TaskReportResponseDTO o) {
        return this.dateCreated.compareTo(o.getDateCreated());
    }
}