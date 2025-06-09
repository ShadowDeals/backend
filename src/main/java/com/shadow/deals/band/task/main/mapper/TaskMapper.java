package com.shadow.deals.band.task.main.mapper;

import com.shadow.deals.band.task.main.dto.request.CreateTaskRequestDTO;
import com.shadow.deals.band.task.main.dto.response.TaskResponseDTO;
import com.shadow.deals.band.task.main.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Kirill "Tamada" Simovin
 */
@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    Task toEntity(CreateTaskRequestDTO createTaskDTO);

    @Mapping(target = "taskId", source = "id")
    @Mapping(target = "customer", source = "customer.nickname")
    @Mapping(target = "taskType", source = "type.taskType")
    @Mapping(target = "taskStatus", source = "status.taskStatus")
    @Mapping(target = "reportId", source = "report.id")
    @Mapping(target = "officer", ignore = true)
    @Mapping(target = "executors", ignore = true)
    @Mapping(target = "cancelTaskStatus", source = "cancelStatus.taskStatus")
    TaskResponseDTO toResponseDTO(Task task);
}
