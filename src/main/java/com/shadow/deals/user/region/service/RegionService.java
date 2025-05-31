package com.shadow.deals.user.region.service;

import com.shadow.deals.base.service.CommonEntityService;
import com.shadow.deals.user.region.entity.Region;
import com.shadow.deals.user.region.enums.RegionName;

/**
 * @author Kirill "Tamada" Simovin
 */
public interface RegionService extends CommonEntityService<Region> {
    Region findByRegionName(RegionName regionName);
}
