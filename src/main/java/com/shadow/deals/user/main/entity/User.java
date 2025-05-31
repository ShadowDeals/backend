package com.shadow.deals.user.main.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shadow.deals.band.entity.Band;
import com.shadow.deals.base.entity.BaseIdEntity;
import com.shadow.deals.user.activation.entity.ActivationCode;
import com.shadow.deals.user.refresh.entity.RefreshToken;
import com.shadow.deals.user.role.entity.UserRole;
import com.shadow.deals.util.constant.CommonConstantHolder;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * This class describe user and contains info about user.
 *
 * @author Kirill "Tamada" Simovin
 */
@Getter
@Setter
@Serdeable
@Entity
@Table(name = CommonConstantHolder.TABLE_PREFIX + "user")
public class User extends BaseIdEntity {
    /**
     * User's email.
     */
    @Column(name = "email", unique = true)
    private String email;

    /**
     * User's password.
     */
    @JsonIgnore
    @Column(name = "password")
    private String password;

    /**
     * User's first name.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * User's last name.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * User's nickname.
     */
    @Column(name = "nickname")
    private String nickname;
    /**
     * User's refresh token.
     */
    @OneToOne(mappedBy = "user")
    private RefreshToken refreshToken;

    /**
     * User's activation token.
     */
    @OneToOne(mappedBy = "user")
    private ActivationCode activationCode;

    @OneToOne(mappedBy = "don")
    private Band ownBand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "band_id", referencedColumnName = "id")
    private Band band;

    /**
     * A set of user roles that provide them with certain capabilities.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private UserRole role;
}