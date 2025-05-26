package com.shadow.deals.user.activation.entity;

import com.shadow.deals.base.entity.id.BaseIdEntity;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.util.constant.CommonConstantHolder;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * This class describe user activation code.
 *
 * @author Kirill "Tamada" Simovin
 */
@Getter
@Setter
@Serdeable
@Entity
@Table(name = CommonConstantHolder.TABLE_PREFIX + "activation_code")
public class ActivationCode extends BaseIdEntity {
    /**
     * Activation code value.
     */
    @Column(name = "activation_code")
    private String activationCode;

    /**
     * Whether the mail is activated.
     */
    @Column(name = "is_activated")
    private boolean isActivated;


    ////////////////////// Mapping //////////////////////

    /**
     * The user that this code is associated with.
     */
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    ////////////////////// Mapping //////////////////////
}