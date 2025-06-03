package com.shadow.deals.band.main.service;

import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.base.service.CommonEntityService;
import com.shadow.deals.region.enums.RegionName;
import com.shadow.deals.user.main.dto.response.BandWorkerResponseDTO;
import com.shadow.deals.user.role.enums.UserRoleName;

import java.util.TreeSet;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
public interface BandService extends CommonEntityService<Band> {
    Band findByRegion(RegionName region);

    TreeSet<BandWorkerResponseDTO> getWorkersByType(UUID bandId, UserRoleName userRole);
}
