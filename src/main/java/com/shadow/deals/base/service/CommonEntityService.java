package com.shadow.deals.base.service;

import com.shadow.deals.base.entity.BaseIdEntity;

import java.util.List;
import java.util.UUID;

/**
 * This interface is the basic service from which the others are inherited. This interface provides methods that must be
 * implemented by each service.
 *
 * @param <E> the class corresponding to the entity that needs the service. The class must extend {@link BaseIdEntity}.
 * @author Kirill "Tamada" Simovin
 */
public interface CommonEntityService<E extends BaseIdEntity> {
    /**
     * This signature describes a method that allows to find an entity by its Id.
     *
     * @param id entity's {@link UUID} Id.
     * @return The entity corresponding to the given Id.
     */
    E findById(UUID id);

    /**
     * This signature describes a method that allows to get all the elements from the table corresponding to the entity described by the Generic class.
     *
     * @return All the elements from the table corresponding to the entity described by the Generic class.
     */
    List<E> findAll();

    /**
     * This signature describes a method that allows to save an entity to the appropriate table.
     *
     * @param entity entity to save.
     * @return Saved entity with set ID and other auto generated values.
     * @throws UnsupportedOperationException in case the method is not implemented.
     */
    default E save(E entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}