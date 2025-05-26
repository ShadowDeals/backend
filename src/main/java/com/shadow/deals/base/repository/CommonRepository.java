package com.shadow.deals.base.repository;

import com.shadow.deals.base.entity.id.BaseIdEntity;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;

import java.util.UUID;

/**
 * This interface is the base repository from which the others are inherited.
 *
 * @param <E> the class corresponding to the entity that needs the repository. The class must extend {@link BaseIdEntity}.
 * @author Kirill "Tamada" Simovin
 */
public interface CommonRepository<E extends BaseIdEntity> extends ReactiveStreamsCrudRepository<E, UUID> {
}
