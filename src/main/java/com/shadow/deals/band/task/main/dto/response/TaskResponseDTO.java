package com.shadow.deals.band.task.main.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shadow.deals.band.task.status.enums.TaskStatusEnum;
import com.shadow.deals.band.task.type.enums.TaskTypeEnum;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Introspected
@Data
@AllArgsConstructor
@NoArgsConstructor
@SerdeImport(TaskResponseDTO.class)
public class TaskResponseDTO implements Comparable<TaskResponseDTO> {
    @JsonProperty("taskId")
    private UUID taskId;

    @JsonProperty("customerId")
    private UUID customerId;

    @JsonProperty("customer")
    private String customer;

    @JsonProperty("address")
    private String address;

    @JsonProperty("description")
    private String description;

    @JsonProperty("dateCreated")
    private Instant dateCreated;

    @JsonProperty("taskType")
    private TaskTypeEnum taskType;

    @JsonProperty("taskStatus")
    private TaskStatusEnum taskStatus;

    @JsonProperty("officer")
    private TaskExecutorResponseDTO officer;

    @JsonProperty("price")
    private int price;

    @JsonProperty("reportId")
    private UUID reportId;

    @JsonProperty("executors")
    private List<TaskExecutorResponseDTO> executors;

    @JsonProperty("cancelReason")
    private String cancelReason;

    @JsonProperty("cancelTaskStatus")
    private TaskStatusEnum cancelTaskStatus;

    @Override
    public int compareTo(@NotNull TaskResponseDTO o) {
        return dateCreated.compareTo(o.getDateCreated());
    }
}
