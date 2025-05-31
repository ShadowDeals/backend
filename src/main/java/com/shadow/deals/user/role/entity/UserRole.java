package com.shadow.deals.user.role.entity;

import com.shadow.deals.base.entity.BaseIdEntity;
import com.shadow.deals.user.role.enums.UserRoleName;
import com.shadow.deals.util.constant.CommonConstantHolder;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * This class describe user role.
 *
 * @author Kirill "Tamada" Simovin
 */
@Getter
@Setter
@Serdeable
@Entity
@Table(name = CommonConstantHolder.TABLE_PREFIX + "user_role")
public class UserRole extends BaseIdEntity {
    /**
     * User role name.
     */
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private UserRoleName roleName;
}