package com.shadow.deals.band.param.name.enums;

import com.shadow.deals.base.enums.CommonEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Kirill "Tamada" Simovin
 */
@Getter
@RequiredArgsConstructor
@ToString
public enum BandParamNameEnum implements CommonEnum<BandParamNameEnum> {
    WORKERS("Workers"),
    PROFIT("Profit");

    private final String title;
}
