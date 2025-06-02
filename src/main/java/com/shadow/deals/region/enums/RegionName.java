package com.shadow.deals.region.enums;

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
public enum RegionName implements CommonEnum<RegionName> {
    VASILEOSTROVKIY_REGION("Василеостровский район"),
    MOSCOW_REGION("Московский район"),
    VIBORGSKY_REGION("Выборгский район");

    private final String title;
}
