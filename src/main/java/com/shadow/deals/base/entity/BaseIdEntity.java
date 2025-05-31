package com.shadow.deals.base.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * This class is superclass and provide {@link Id} column for entities.
 *
 * @author Kirill "Tamada" Simovin
 */
@Getter
@Setter
@MappedSuperclass
public class BaseIdEntity implements IHasId {
    /**
     * Primary key - {@link UUID} identifier.
     */
    @Id
    @JsonProperty("id")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;
}