package com.shadow.deals.band.task.status.enums;

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
public enum TaskStatusEnum implements CommonEnum<TaskStatusEnum> {
    WAITING_FOR_ACCEPT("На рассмотрении"),
    WAITING_FOR_PAYMENT("Ожидающие оплаты"),
    WAITING_FOR_ASSIGNMENT("Ожидающие назначения"),
    IN_WORK("В работе"),
    FINISHED("Завершенные");

    private final String title;
}
