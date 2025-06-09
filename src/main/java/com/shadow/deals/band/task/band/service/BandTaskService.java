package com.shadow.deals.band.task.band.service;

import com.shadow.deals.band.task.band.entity.BandTask;
import com.shadow.deals.band.task.main.entity.Task;
import com.shadow.deals.base.service.CommonEntityService;

import java.util.List;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
public interface BandTaskService extends CommonEntityService<BandTask> {
    void deleteByTaskExcludeBand(UUID taskId, UUID bandId);

    List<Task> findAllTasksByBand(UUID bandId);

    List<BandTask> findAllByCustomer(UUID customerId);

    List<BandTask> findAllByTask(Task task);
}
