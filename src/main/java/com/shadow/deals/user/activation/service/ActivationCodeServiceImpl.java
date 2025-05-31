package com.shadow.deals.user.activation.service;

import com.shadow.deals.base.service.CommonUpdateService;
import com.shadow.deals.exception.APIException;
import com.shadow.deals.user.activation.entity.ActivationCode;
import com.shadow.deals.user.activation.repository.ActivationCodeRepository;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * This class contains methods that perform business logic related to the {@link ActivationCode} entity.
 *
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class ActivationCodeServiceImpl implements ActivationCodeService, CommonUpdateService<ActivationCode> {
    /**
     * Instance of the repository interface that contains methods for interacting with table associated with
     * {@link ActivationCode} entity.
     */
    private final ActivationCodeRepository activationCodeRepository;

    /**
     * This method allows to find an {@link ActivationCode} entity by its Id.
     *
     * @param id entity's {@link UUID} Id.
     * @return The {@link ActivationCode} entity corresponding to the given Id.
     * @throws APIException in case the requested instance of the entity does not exist. It throws with the
     *                      {@link HttpStatus#NOT_FOUND} code.
     */
    @Override
    public ActivationCode findById(UUID id) {
        return activationCodeRepository.findById(id).orElseThrow(() -> new APIException(
                "Кода активации с id = %s не существует".formatted(id),
                HttpStatus.NOT_FOUND)
        );
    }

    /**
     * This method allows to get all the elements from the table corresponding to the {@link ActivationCode}
     * entity described by the Generic class.
     *
     * @return All the elements from the table corresponding to the {@link ActivationCode} entity described
     * by the Generic class.
     */
    @Override
    public List<ActivationCode> findAll() {
        return activationCodeRepository.findAll();
    }

    /**
     * This method allows to save an entity to the appropriate table.
     *
     * @param entity entity to save.
     * @return Saved entity with set ID and other auto generated values.
     */
    @Override
    public ActivationCode save(ActivationCode entity) {
        return activationCodeRepository.save(entity);
    }

    /**
     * This method allows to find an entity by the value of the activation code.
     *
     * @param activationCode the activation code for email confirmation.
     * @return The entity corresponding to the given activation code.
     * @throws APIException in case a non-existent activation code is passed. It throws with the
     *                      {@link HttpStatus#NOT_FOUND} code.
     */
    @Override
    public ActivationCode findByActivationCode(String activationCode) {
        return activationCodeRepository.findByActivationCode(activationCode).orElseThrow(() -> new APIException(
                "Кода активации со значением %s не существует".formatted(activationCode),
                HttpStatus.NOT_FOUND)
        );
    }

    /**
     * This method allows updating the fields of the transferred entity in the database.
     *
     * @param entity the entity that needs to be updated.
     * @return Updated entity.
     */
    @Override
    public ActivationCode update(ActivationCode entity) {
        return activationCodeRepository.update(entity);
    }
}