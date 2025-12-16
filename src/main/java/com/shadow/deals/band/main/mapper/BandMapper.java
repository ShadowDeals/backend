package com.shadow.deals.band.main.mapper;

import com.shadow.deals.band.main.dto.response.BandInfoResponseDTO;
import com.shadow.deals.band.main.entity.Band;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Kirill "Tamada" Simovin
 */
@Mapper
public interface BandMapper {

    /**
     * Instance of this interface.
     */
    BandMapper INSTANCE = Mappers.getMapper(BandMapper.class);

    @Mapping(target = "region", source = "region.regionName")
    BandInfoResponseDTO toResponseDTO(Band band);
}