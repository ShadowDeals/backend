package com.shadow.deals.user.activation.service;


import com.shadow.deals.base.service.CommonEntityService;
import com.shadow.deals.user.activation.entity.ActivationCode;

/**
 * This interface contains the signatures of the methods that must be implemented by the corresponding service class.
 *
 * @author Kirill "Tamada" Simovin
 */
public interface ActivationCodeService extends CommonEntityService<ActivationCode> {
    /**
     * This signature describes a method that allows to find an entity by the value of the activation code.
     *
     * @param activationCode the activation code for email confirmation.
     * @return The entity corresponding to the given activation code.
     */
    ActivationCode findByActivationCode(String activationCode);
}