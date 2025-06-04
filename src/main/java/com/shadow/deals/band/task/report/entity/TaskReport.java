package com.shadow.deals.band.task.report.entity;

import com.shadow.deals.band.task.main.entity.Task;
import com.shadow.deals.base.entity.BaseIdEntity;
import com.shadow.deals.util.constant.CommonConstantHolder;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = CommonConstantHolder.TABLE_PREFIX + "task_report")
public class TaskReport extends BaseIdEntity {
    @Column(name = "description", length = 1024)
    private String description;

    @Column(name = "time_spent")
    private int timeSpent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;
}