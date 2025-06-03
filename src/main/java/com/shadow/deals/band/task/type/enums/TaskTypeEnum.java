package com.shadow.deals.band.task.type.enums;

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
public enum TaskTypeEnum implements CommonEnum<TaskTypeEnum> {
    HIJACKING("Угон"),
    MURDER("Убийство"),
    ROBBERY("Грабеж"),
    SCARING("Припугивание"),
    DELIVERY("Доставка");

    private final String title;
}
