package com.github.pieter_groenendijk.service.notification.scheduling;

import com.github.pieter_groenendijk.utils.scheduling.TaskScheduler;
import com.github.pieter_groenendijk.model.notification.NotificationTask;
import com.github.pieter_groenendijk.repository.notification.INotificationTaskRepository;
import com.github.pieter_groenendijk.service.notification.send_strategies.registry.NotificationSendStrategyRegistry;
import com.github.pieter_groenendijk.service.notification.task.NotificationTaskStatus;
import com.github.pieter_groenendijk.service.notification.task.UnprocessedNotificationTask;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

public class NotificationTaskScheduler {
    private final TaskScheduler SCHEDULER;
    private final INotificationTaskRepository REPOSITORY;
    private final NotificationSendStrategyRegistry SEND_STRATEGY_REGISTRY;

    private final Duration RETRIEVE_INTERVAL = Duration.ofMinutes(5);
    private final HashSet<NotificationTask> HANDLED_MANUALLY = new HashSet<>();

    public NotificationTaskScheduler(TaskScheduler scheduler, INotificationTaskRepository repository, NotificationSendStrategyRegistry sendStrategyRegistry) {
        this.SCHEDULER = scheduler;
        this.REPOSITORY = repository;
        this.SEND_STRATEGY_REGISTRY = sendStrategyRegistry;

        startSchedulingFromDatabase();
    }

    public void schedule(UnprocessedNotificationTask unprocessedTask) {
        NotificationTask task = unprocessedTask.store();

        if (shouldScheduleInMemory(task)) {
            scheduleDirectlyInMemory(task);
        }
    }

    private void startSchedulingFromDatabase() {
        this.SCHEDULER.scheduleRecurring(
            this::scheduleFromDatabase,
            this.RETRIEVE_INTERVAL
        );
    }

    private void scheduleFromDatabase() {
        List<NotificationTask> tasks = this.REPOSITORY.retrieve(this.getScheduledUntilDateTime());

        tasks.forEach(this::maybeScheduleFromDatabase);
    }

    private void maybeScheduleFromDatabase(NotificationTask task) {
        if (isHandledManually(task)) { // TODO: Maybe it's worth not having directScheduling(.scheduleDirectlyInMemory()), a low enough retrieve interval could make it okay.
            HANDLED_MANUALLY.remove(task);
            return;
        }

        scheduleInMemory(task);
    }

    private boolean shouldScheduleInMemory(NotificationTask task) {
        return task.isScheduledBefore(
            this.getScheduledUntilDateTime()
        );
    }

    private void scheduleDirectlyInMemory(NotificationTask task) {
        this.HANDLED_MANUALLY.add(task);

        this.scheduleInMemory(task);
    }

    private void scheduleInMemory(NotificationTask task) {
        this.SCHEDULER.schedule(
            () -> this.executeTask(task),
            task.scheduledAt
        );
    }

    private void executeTask(NotificationTask task) {
        this.SEND_STRATEGY_REGISTRY
            .fromStrategyType(task.sendStrategyType)
            .send(task);

        this.REPOSITORY.updateStatus(task, NotificationTaskStatus.EXECUTED);
    }

    private boolean isHandledManually(NotificationTask task) {
        return HANDLED_MANUALLY.contains(task);
    }

    private LocalDateTime getScheduledUntilDateTime() {
        return LocalDateTime.now().plus(this.RETRIEVE_INTERVAL);
    }
}
