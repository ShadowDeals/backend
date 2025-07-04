package com.shadow.deals.band.task.report.mapper;

import com.shadow.deals.band.task.report.dto.request.TaskReportRequestDTO;
import com.shadow.deals.band.task.report.dto.response.TaskReportResponseDTO;
import com.shadow.deals.band.task.report.entity.TaskReport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Kirill "Tamada" Simovin
 */
@Mapper
public interface TaskReportMapper {
    TaskReportMapper INSTANCE = Mappers.getMapper(TaskReportMapper.class);

    TaskReport toEntity(TaskReportRequestDTO dto);

    @Mapping(target = "reportId", source = "id")
    @Mapping(target = "taskId", source = "task.id")
    @Mapping(target = "taskStatus", source = "task.status.taskStatus")
    TaskReportResponseDTO toResponseDTO(TaskReport report);
}