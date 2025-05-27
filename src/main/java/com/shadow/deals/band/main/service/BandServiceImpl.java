package com.shadow.deals.band.main.service;

import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.band.main.repository.BandRepository;
import com.shadow.deals.band.param.main.entity.BandParam;
import com.shadow.deals.band.param.name.entity.BandParamName;
import com.shadow.deals.band.param.name.enums.BandParamNameEnum;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class BandServiceImpl implements BandService {
    private final BandRepository bandRepository;

    @Override
    public Flux<Band> findById(UUID id) {
        return Flux.from(bandRepository.findById(id));
    }

    @Override
    public Flux<Band> findAll() {
        return Flux.from(bandRepository.findAll());
    }

    @Override
    public Flux<BandParam> getBandMockParams() {
        return Flux.just(
                createBandParam(2020, "10"),
                createBandParam(2021, "11"),
                createBandParam(2022, "12"),
                createBandParam(2023, "13"),
                createBandParam(2024, "14")
        );
    }

    private @NotNull BandParam createBandParam(int year, String value) {
        BandParam bandParam = new BandParam();
        bandParam.setDate(LocalDate.of(year, 1, 1));
        bandParam.setValue(value);

        BandParamName bandParamName = new BandParamName();
        bandParamName.setParamName(BandParamNameEnum.WORKERS);
        bandParam.setParamName(bandParamName);
        return bandParam;
    }
}
