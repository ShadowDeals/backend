package com.shadow.deals.band.task.report.mapper;

import com.shadow.deals.band.task.report.dto.request.TaskReportRequestDTO;
import com.shadow.deals.band.task.report.entity.TaskReport;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Kirill "Tamada" Simovin
 */
@Mapper
public interface TaskReportMapper {
    TaskReportMapper INSTANCE = Mappers.getMapper(TaskReportMapper.class);

    TaskReport toEntity(TaskReportRequestDTO dto);
}