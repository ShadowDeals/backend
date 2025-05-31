package com.shadow.deals.band.entity;

import com.shadow.deals.base.entity.BaseIdEntity;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.util.constant.CommonConstantHolder;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    @OneToOne
    @JoinColumn(name = "don_id", referencedColumnName = "id")
    private User don;

    @OneToMany(mappedBy = "band")
    private Set<User> workers;
}