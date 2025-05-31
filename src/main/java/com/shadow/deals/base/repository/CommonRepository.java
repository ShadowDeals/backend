package com.shadow.deals.base.repository;

import com.shadow.deals.base.entity.BaseIdEntity;
import io.micronaut.data.repository.CrudRepository;

import java.util.UUID;

/**
 * This interface is the base repository from which the others are inherited.
 *
 * @param <E> the class corresponding to the entity that needs the repository. The class must extend {@link BaseIdEntity}.
 * @author Kirill "Tamada" Simovin
 */
public interface CommonRepository<E extends BaseIdEntity> extends CrudRepository<E, UUID> {
}
