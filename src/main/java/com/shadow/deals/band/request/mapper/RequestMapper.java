package com.shadow.deals.band.request.mapper;

import com.shadow.deals.band.request.dto.response.OwnRequestResponseDTO;
import com.shadow.deals.band.request.dto.response.RequestResponseDTO;
import com.shadow.deals.band.request.entity.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Kirill "Tamada" Simovin
 */
@Mapper
public interface RequestMapper {
    /**
     * Instance of this interface.
     */
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    @Mapping(target = "userRole", source = "request.user.role.roleName")
    RequestResponseDTO toResponseDTO(Request request, String name);

    @Mapping(target = "bandRegion", source = "band.region.regionName")
    @Mapping(target = "bandId", source = "band.id")
    OwnRequestResponseDTO toOwnResponseDTO(Request request);
}