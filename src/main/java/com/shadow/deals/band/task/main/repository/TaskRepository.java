package com.shadow.deals.band.task.main.repository;

import com.shadow.deals.base.repository.CommonRepository;
import com.shadow.deals.band.task.main.entity.Task;
import com.shadow.deals.user.main.entity.User;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
@Repository
public interface TaskRepository extends CommonRepository<Task> {
    @Query(value = """
            DELETE FROM sd_band_task WHERE task_id = :taskId AND band_id = :bandId;
            """,
            nativeQuery = true)
    void deleteByIdAndBandId(UUID taskId, UUID bandId);

    List<Task> findByCustomer(User customer);
}
