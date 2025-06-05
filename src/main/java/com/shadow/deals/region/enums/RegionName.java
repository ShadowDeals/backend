package com.shadow.deals.region.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.shadow.deals.base.enums.CommonEnum;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Kirill "Tamada" Simovin
 */
@Getter(onMethod = @__(@JsonValue))
@RequiredArgsConstructor
@Introspected
@SerdeImport(RegionName.class)
@ToString
public enum RegionName implements CommonEnum<RegionName> {
    VASILEOSTROVKIY_REGION("Василеостровский район"),
    MOSCOW_REGION("Московский район"),
    VIBORGSKY_REGION("Выборгский район");

    private final String title;
}
