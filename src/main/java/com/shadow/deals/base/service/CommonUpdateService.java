package com.shadow.deals.base.service;

import com.shadow.deals.base.entity.BaseIdEntity;

/**
 * This interface provides a method for updating entity fields.
 *
 * @param <E> the class corresponding to the entity that needs the service. The class must extend {@link BaseIdEntity}.
 * @author Kirill "Tamada" Simovin
 */
public interface CommonUpdateService<E extends BaseIdEntity> extends CommonEntityService<E> {
    /**
     * This signature describes a method that allows updating the fields of the transferred entity in the database.
     *
     * @param entity the entity that needs to be updated.
     * @return Updated entity.
     */
    E update(E entity);
}