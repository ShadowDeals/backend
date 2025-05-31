package com.shadow.deals.base.entity;

import java.util.UUID;

/**
 * This interface provide getter and setter for entity {@link jakarta.persistence.Id} column.
 *
 * @author Kirill "Tamada" Simovin
 */
public interface IHasId {
    /**
     * This method allows to get entity identifier.
     *
     * @return {@link UUID} identifier.
     */
    UUID getId();

    /**
     * This method allows to set entity identifier.
     *
     * @param id {@link UUID} identifier.
     */
    void setId(UUID id);
}