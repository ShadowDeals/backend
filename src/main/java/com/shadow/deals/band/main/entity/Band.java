package com.shadow.deals.band.main.entity;

import com.shadow.deals.band.param.main.entity.BandParam;
import com.shadow.deals.base.entity.id.BaseIdEntity;
import com.shadow.deals.util.constant.CommonConstantHolder;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = CommonConstantHolder.TABLE_PREFIX + "band")
public class Band extends BaseIdEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "band")
    private Set<BandParam> bandParams;
}