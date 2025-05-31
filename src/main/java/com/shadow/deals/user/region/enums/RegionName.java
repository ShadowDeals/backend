package com.shadow.deals.user.region.enums;

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
    VIBORGKY_REGION("Выборгский район");

    private final String title;
}
