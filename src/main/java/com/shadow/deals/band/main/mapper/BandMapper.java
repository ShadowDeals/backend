package com.shadow.deals.band.main.mapper;

import com.shadow.deals.band.main.dto.response.BandInfoResponseDTO;
import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.user.main.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Kirill "Tamada" Simovin
 */
@Mapper(uses = {UserMapper.class})
public interface BandMapper {
    /**
     * Instance of this interface.
     */
    BandMapper INSTANCE = Mappers.getMapper(BandMapper.class);

    @Mapping(target = "donId", source = "don.id")
    @Mapping(target = "region", source = "region.regionName")
    BandInfoResponseDTO toResponseDTO(Band band);
}