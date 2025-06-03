package com.shadow.deals.band.task.status.entity;

import com.shadow.deals.base.entity.BaseIdEntity;
import com.shadow.deals.band.task.status.enums.TaskStatusEnum;
import com.shadow.deals.util.constant.CommonConstantHolder;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Kirill "Tamada" Simovin
 */
@Getter
@Setter
@Serdeable
@Entity
@Table(name = CommonConstantHolder.TABLE_PREFIX + "task_status")
public class TaskStatus extends BaseIdEntity {
    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatusEnum taskStatus;
}
