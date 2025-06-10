package com.shadow.deals.band.blocked.service;

import com.shadow.deals.band.blocked.dto.request.BlockBandRequestDTO;
import com.shadow.deals.band.blocked.entity.BlockedBand;
import com.shadow.deals.base.service.CommonEntityService;
import io.micronaut.http.HttpRequest;

import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
public interface BlockedBandService extends CommonEntityService<BlockedBand> {
    boolean existsByBandId(UUID bandId);

    void blockDb(BlockBandRequestDTO blockBandRequestDTO, HttpRequest<?> request);

    void unblockDb(BlockBandRequestDTO blockBandRequestDTO, HttpRequest<?> request);

    boolean getDbState(HttpRequest<?> request);
}
