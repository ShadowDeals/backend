package com.shadow.deals.band.main.service;

import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.base.service.CommonEntityService;
import com.shadow.deals.region.enums.RegionName;

/**
 * @author Kirill "Tamada" Simovin
 */
public interface BandService extends CommonEntityService<Band> {
    Band findByRegion(RegionName region);
}
