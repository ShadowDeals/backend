package com.shadow.deals.band.task.main.entity;

import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.band.task.report.entity.TaskReport;
import com.shadow.deals.band.task.status.entity.TaskStatus;
import com.shadow.deals.band.task.type.entity.TaskType;
import com.shadow.deals.base.entity.BaseIdEntity;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.util.constant.CommonConstantHolder;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

/**
 * @author Kirill "Tamada" Simovin
 */
@Getter
@Setter
@Serdeable
@Entity
@Table(name = CommonConstantHolder.TABLE_PREFIX + "task")
public class Task extends BaseIdEntity {
    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "officer_id", referencedColumnName = "id")
    private User officer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private TaskType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private TaskStatus status;

    @DateCreated
    @Column(name = "date_created")
    private Instant dateCreated;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = CommonConstantHolder.TABLE_PREFIX + "band_task",
            joinColumns = @JoinColumn(name = "band_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    private Set<Band> bands;

    @OneToMany(mappedBy = "task")
    private Set<User> executors;

    @OneToOne(mappedBy = "task")
    private TaskReport report;
}
