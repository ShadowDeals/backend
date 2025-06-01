package com.shadow.deals.band.request.entity;

import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.base.entity.BaseIdEntity;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.util.constant.CommonConstantHolder;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * @author Kirill "Tamada" Simovin
 */
@Getter
@Setter
@Serdeable
@Entity
@Table(name = CommonConstantHolder.TABLE_PREFIX + "band_request")
public class Request extends BaseIdEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "band_id", referencedColumnName = "id")
    private Band band;

    @DateCreated
    @Column(name = "date_created")
    private Instant dateCreated;
}