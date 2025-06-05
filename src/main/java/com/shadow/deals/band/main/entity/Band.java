package com.shadow.deals.band.main.entity;

import com.shadow.deals.band.request.entity.Request;
import com.shadow.deals.band.task.main.entity.Task;
import com.shadow.deals.base.entity.BaseIdEntity;
import com.shadow.deals.region.entity.Region;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.util.constant.CommonConstantHolder;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    @OneToOne(mappedBy = "ownBand")
    private User don;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private Region region;

    @OneToMany(mappedBy = "band")
    private Set<User> workers;

    @OneToMany(mappedBy = "band")
    private Set<Request> requests;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Task> tasks;
}