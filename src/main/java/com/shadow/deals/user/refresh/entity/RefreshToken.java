package com.shadow.deals.user.refresh.entity;

import com.shadow.deals.base.entity.BaseIdEntity;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.util.constant.CommonConstantHolder;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * This class describe user refresh token. </br>
 *
 * @author Kirill "Tamada" Simovin
 * @see <a href="https://stackoverflow.com/a/59511813">Refresh token logic</a>.
 */
@Getter
@Setter
@Serdeable
@Entity
@Table(name = CommonConstantHolder.TABLE_PREFIX + "refresh_token")
public class RefreshToken extends BaseIdEntity {
    /**
     * Refresh token value.
     */
    @Column(name = "refresh_token")
    private String refreshToken;

    /**
     * When refresh token was issued.
     */
    @DateCreated
    @Column(name = "date_created")
    private Instant dateCreated;


    ////////////////////// Mapping //////////////////////

    /**
     * The user that this token is associated with.
     */
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;

    ////////////////////// Mapping //////////////////////
}