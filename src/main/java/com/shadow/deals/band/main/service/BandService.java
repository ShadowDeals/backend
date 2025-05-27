package com.shadow.deals.band.main.service;

import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.band.param.main.entity.BandParam;
import com.shadow.deals.base.service.CommonEntityService;
import reactor.core.publisher.Flux;

/**
 * @author Kirill "Tamada" Simovin
 */
public interface BandService extends CommonEntityService<Band> {
    Flux<BandParam> getBandMockParams();
}
