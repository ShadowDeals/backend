package com.shadow.deals.user.role.enums;

import com.shadow.deals.base.enums.CommonEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * This Enum contains all supported user roles.
 *
 * @author Kirill "Tamada" Simovin
 */
@Getter
@RequiredArgsConstructor
@ToString
public enum UserRoleName implements CommonEnum<UserRoleName> {
    USER("Пользователь"),
    DON("Дон"),
    ADMIN("Администратор"),
    SOLDIER("Солдат"),
    SUPER_ADMIN("Главный администратор");

    /**
     * The Enum element string value.
     */
    private final String title;
}