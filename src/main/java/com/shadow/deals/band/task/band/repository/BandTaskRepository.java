package com.shadow.deals.band.task.band.repository;

import com.shadow.deals.band.task.band.entity.BandTask;
import com.shadow.deals.band.task.main.entity.Task;
import com.shadow.deals.base.repository.CommonRepository;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Repository
public interface BandTaskRepository extends CommonRepository<BandTask> {
    @Query("DELETE FROM BandTask bt WHERE bt.task.id = :taskId AND bt.band.id <> :bandId")
    void deleteByTaskExcludeBand(UUID taskId, UUID bandId);

//    @Query(value = "SELECT st.* FROM sd_task st WHERE st.id IN (SELECT sbt.task_id FROM sd_band_task sbt WHERE sbt.band_id = :bandId)", nativeQuery = true)
    @Query("SELECT t FROM Task t WHERE t.id IN (SELECT bt.task.id FROM BandTask bt WHERE bt.band.id = :bandId)")
    List<Task> findAllTasksByBand(UUID bandId);

    @Query("SELECT bt FROM BandTask bt WHERE bt.task.customer.id = :customerId")
    List<BandTask> findAllByCustomer(UUID customerId);

    List<BandTask> findAllByTask(Task task);
}
