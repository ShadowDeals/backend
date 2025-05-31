package com.shadow.deals.user.region.entity;

import com.shadow.deals.base.entity.BaseIdEntity;
import com.shadow.deals.user.region.enums.RegionName;
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
 * @author Kirill "Tamada" Simovin
 */
@Getter
@Setter
@Serdeable
@Entity
@Table(name = CommonConstantHolder.TABLE_PREFIX + "region")
public class Region extends BaseIdEntity {
    @Column(name = "region_name")
    @Enumerated(EnumType.STRING)
    private RegionName regionName;
}