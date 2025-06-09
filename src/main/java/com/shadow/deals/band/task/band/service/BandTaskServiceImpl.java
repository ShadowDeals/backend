package com.shadow.deals.band.task.band.service;

import com.shadow.deals.band.task.band.entity.BandTask;
import com.shadow.deals.band.task.band.repository.BandTaskRepository;
import com.shadow.deals.band.task.main.entity.Task;
import com.shadow.deals.exception.APIException;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class BandTaskServiceImpl implements BandTaskService {
    private final BandTaskRepository bandTaskRepository;

    @Override
    public BandTask findById(UUID id) {
        return bandTaskRepository.findById(id).orElseThrow(() -> new APIException(
                "Связи задания с бандой с id = %s не найдено".formatted(id),
                HttpStatus.NOT_FOUND));
    }

    @Override
    public List<BandTask> findAll() {
        return bandTaskRepository.findAll();
    }

    @Override
    public BandTask save(BandTask entity) {
        return bandTaskRepository.save(entity);
    }

    @Override
    public void deleteByTaskExcludeBand(UUID taskId, UUID bandId) {
        bandTaskRepository.deleteByTaskExcludeBand(taskId, bandId);
    }

    @Override
    public List<Task> findAllTasksByBand(UUID bandId) {
        return bandTaskRepository.findAllTasksByBand(bandId);
    }

    @Override
    public List<BandTask> findAllByCustomer(UUID customerId) {
        return bandTaskRepository.findAllByCustomer(customerId);
    }

    @Override
    public List<BandTask> findAllByTask(Task task) {
        return bandTaskRepository.findAllByTask(task);
    }
}
