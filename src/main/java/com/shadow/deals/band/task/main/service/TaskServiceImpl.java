package com.shadow.deals.band.task.main.service;

import com.shadow.deals.band.blocked.service.BlockedBandService;
import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.band.main.service.BandService;
import com.shadow.deals.band.task.band.entity.BandTask;
import com.shadow.deals.band.task.band.service.BandTaskService;
import com.shadow.deals.band.task.dto.request.CreateTaskRequestDTO;
import com.shadow.deals.band.task.dto.response.TaskResponseDTO;
import com.shadow.deals.band.task.main.entity.Task;
import com.shadow.deals.band.task.main.repository.TaskRepository;
import com.shadow.deals.band.task.mapper.TaskMapper;
import com.shadow.deals.band.task.status.enums.TaskStatusEnum;
import com.shadow.deals.band.task.status.service.TaskStatusService;
import com.shadow.deals.band.task.type.service.TaskTypeService;
import com.shadow.deals.exception.APIException;
import com.shadow.deals.region.enums.RegionName;
import com.shadow.deals.user.main.dto.response.FreeExecutorResponseDTO;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.user.main.mapper.UserMapper;
import com.shadow.deals.user.main.service.UserServiceImpl;
import com.shadow.deals.user.role.enums.UserRoleName;
import com.shadow.deals.util.CommonUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    private final UserServiceImpl userService;

    private final BandService bandService;

    private final TaskStatusService taskStatusService;

    private final TaskTypeService taskTypeService;

    private final BandTaskService bandTaskService;

    private final BlockedBandService blockedBandService;

    @Override
    public Task findById(UUID id) {
        return taskRepository.findById(id).orElseThrow(() -> new APIException(
                "Задания с id = %s не найдено".formatted(id),
                HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task save(Task entity) {
        return taskRepository.save(entity);
    }

    @Override
    public UUID createTask(CreateTaskRequestDTO createTaskDTO, HttpRequest<?> request) {
        String userEmail = CommonUtils.getUserEmailFromJWTToken(request);
        User user = userService.findByEmail(userEmail);

        Task task = TaskMapper.INSTANCE.toEntity(createTaskDTO);
        task.setCustomer(user);
        task.setStatus(taskStatusService.findByTaskStatus(TaskStatusEnum.WAITING_FOR_ACCEPT));
        task.setType(taskTypeService.findByTaskType(createTaskDTO.getTaskType()));
        task.setDateCreated(Instant.now());
        RegionName region = createTaskDTO.getRegion();

        task = taskRepository.save(task);

        if (region == null) {
            for (Band band : bandService.findAll()) {
                createBandTask(task, band);
            }
            return task.getId();
        }

        Band band = bandService.findByRegion(region);
        createBandTask(task, band);

        return task.getId();
    }

    @Override
    public void updateTaskStatus(UUID taskId, UUID bandId, TaskStatusEnum taskStatus) {
        Task task = findById(taskId);

        if (blockedBandService.existsByBandId(bandId)) {
            throw new APIException("База данных заблокирована!", HttpStatus.LOCKED);
        }

        if (taskStatus == TaskStatusEnum.WAITING_FOR_ASSIGNMENT) {
            bandTaskService.deleteByTaskExcludeBand(taskId, bandId);
        } else if (taskStatus == TaskStatusEnum.FINISHED) {
            task.getExecutors().forEach(executor -> {
                executor.setTask(null);
                userService.update(executor);
            });
        }

        task.setStatus(taskStatusService.findByTaskStatus(taskStatus));
        taskRepository.update(task);
    }

    @Override
    public TreeSet<TaskResponseDTO> getTasksByStatus(UUID bandId, TaskStatusEnum taskStatus) {
        if (blockedBandService.existsByBandId(bandId)) {
            throw new APIException("База данных заблокирована!", HttpStatus.LOCKED);
        }

        return bandTaskService.findAllTasksByBand(bandId)
                .stream()
                .filter(task -> task.getStatus().getTaskStatus() == taskStatus)
                .map(this::mapToResponseDTO)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public TreeSet<TaskResponseDTO> getOwnTask(HttpRequest<?> request) {
        String userEmail = CommonUtils.getUserEmailFromJWTToken(request);
        User user = userService.findByEmail(userEmail);

        if (user.getRole().getRoleName() == UserRoleName.USER) {
            return taskRepository.findByCustomer(user)
                    .stream()
                    .map(this::mapToResponseDTO)
                    .collect(Collectors.toCollection(TreeSet::new));
        }

        Task task = user.getTask();
        if (task != null) {
            return new TreeSet<>(Set.of(mapToResponseDTO(task)));
        }

        Band band = user.getBand();
        if (band == null) {
            throw new APIException("Нет банды!", HttpStatus.BAD_REQUEST);
        }

        if (blockedBandService.existsByBandId(band.getId())) {
            throw new APIException("База данных заблокирована!", HttpStatus.LOCKED);
        }

        return band.getTasks()
                .stream()
                .filter(bandTask -> bandTask.getOfficer().getId().equals(user.getId()))
                .map(this::mapToResponseDTO)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public TaskResponseDTO getTaskById(UUID taskId, HttpRequest<?> request) {
        String userEmail = CommonUtils.getUserEmailFromJWTToken(request);
        User user = userService.findByEmail(userEmail);

        Band band = user.getBand();
        if (band == null) {
            band = user.getOwnBand();
        }
        if (band == null) {
            throw new APIException("Нет банды!", HttpStatus.BAD_REQUEST);
        }

        if (blockedBandService.existsByBandId(band.getId())) {
            throw new APIException("База данных заблокирована!", HttpStatus.LOCKED);
        }
        return mapToResponseDTO(findById(taskId));
    }

    @Override
    public void setExecutors(UUID taskId, UUID officerId, @NotNull Set<UUID> executorsId) {
        Task task = findById(taskId);

        if (task.getStatus().getTaskStatus() != TaskStatusEnum.WAITING_FOR_ASSIGNMENT) {
            throw new APIException("Некорректный статус задания!", HttpStatus.BAD_REQUEST);
        }

        Band band = new ArrayList<>(task.getBands()).getFirst();
        if (blockedBandService.existsByBandId(band.getId())) {
            throw new APIException("База данных заблокирована!", HttpStatus.LOCKED);
        }

        for (UUID executorId : executorsId) {
            User executor = userService.findById(executorId);
            executor.setTask(task);
            userService.update(executor);
        }

        User officer = userService.findById(officerId);
        task.setOfficer(officer);
        taskRepository.update(task);
    }

    @Override
    public TreeSet<FreeExecutorResponseDTO> getFreeExecutors(UUID bandId) {
        Band band = bandService.findById(bandId);
        if (blockedBandService.existsByBandId(band.getId())) {
            throw new APIException("База данных заблокирована!", HttpStatus.LOCKED);
        }

        return band.getWorkers()
                .stream()
                .filter(worker -> worker.getTask() == null && worker.getRole().getRoleName() == UserRoleName.SOLDIER)
                .map(UserMapper.INSTANCE::toFreeExecutorResponseDTO)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public void deleteById(UUID taskId, HttpRequest<?> request) {
        String userEmail = CommonUtils.getUserEmailFromJWTToken(request);
        User user = userService.findByEmail(userEmail);

        Band band;
        if (user.getRole().getRoleName() == UserRoleName.ADMIN) {
            band = user.getBand();
        } else {
            band = user.getOwnBand();
        }

        if (band == null) {
            throw new APIException("Нет банды!", HttpStatus.BAD_REQUEST);
        }

        if (blockedBandService.existsByBandId(band.getId())) {
            throw new APIException("База данных заблокирована!", HttpStatus.LOCKED);
        }

        taskRepository.deleteByIdAndBandId(taskId, band.getId());
        taskRepository.deleteById(taskId);
    }

    private TaskResponseDTO mapToResponseDTO(Task task) {
        if (task == null) {
            return null;
        }

        TaskResponseDTO responseDTO = TaskMapper.INSTANCE.toResponseDTO(task);
        responseDTO.setOfficer(userService.getUserName(task.getCustomer()));
        List<String> executors = task.getExecutors()
                .stream()
                .map(userService::getUserName)
                .toList();
        responseDTO.setExecutors(executors);
        return responseDTO;
    }

    private void createBandTask(Task task, Band band) {
        BandTask bandTask = new BandTask();
        bandTask.setTask(task);
        bandTask.setBand(band);
        bandTaskService.save(bandTask);
    }
}
