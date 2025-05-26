package com.shadow.deals.user.activation.repository;

import com.shadow.deals.base.repository.CommonRepository;
import com.shadow.deals.user.activation.entity.ActivationCode;
import io.micronaut.data.annotation.Repository;
import org.reactivestreams.Publisher;

import java.util.Optional;

/**
 * This repository contains methods for interacting with table associated with entity {@link ActivationCode}.
 *
 * @author Kirill "Tamada" Simovin
 */
@Repository
public interface ActivationCodeRepository extends CommonRepository<ActivationCode> {
    /**
     * This signature describes a method that allows to find an entity by the value of the activation code.
     *
     * @param activationCode the activation code for email confirmation.
     * @return If an entity exists, it is {@link Optional}, containing an entity. Otherwise, it's an empty {@link Optional}.
     */
    Publisher<ActivationCode> findByActivationCode(String activationCode);
}