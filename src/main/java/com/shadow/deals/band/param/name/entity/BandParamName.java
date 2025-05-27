package com.shadow.deals.band.param.name.entity;

import com.shadow.deals.band.param.main.entity.BandParam;
import com.shadow.deals.band.param.name.enums.BandParamNameEnum;
import com.shadow.deals.base.entity.id.BaseIdEntity;
import com.shadow.deals.util.constant.CommonConstantHolder;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author Kirill "Tamada" Simovin
 */
@Getter
@Setter
@Serdeable
@Entity
@Table(name = CommonConstantHolder.TABLE_PREFIX + "band_param_name")
public class BandParamName extends BaseIdEntity {
    @Column(name = "param_name")
    @Enumerated(EnumType.STRING)
    private BandParamNameEnum paramName;

    @OneToMany(mappedBy = "paramName")
    private Set<BandParam> bandParams;
}