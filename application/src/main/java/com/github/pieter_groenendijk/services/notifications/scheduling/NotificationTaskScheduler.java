package com.github.pieter_groenendijk.services.notifications.scheduling;

import com.github.pieter_groenendijk.TaskScheduler;
import com.github.pieter_groenendijk.model.NotificationTask;
import com.github.pieter_groenendijk.services.notifications.send_strategies.registry.NotificationSendStrategyRegistry;
import com.github.pieter_groenendijk.services.notifications.task.UnprocessedNotificationTask;
import com.github.pieter_groenendijk.storage.notifications.NotificationTaskRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;

public class NotificationTaskScheduler {
    private final TaskScheduler SCHEDULER;
    private final NotificationTaskRepository REPOSITORY;
    private final NotificationSendStrategyRegistry SEND_STRATEGY_REGISTRY;

    private final Duration RETRIEVE_INTERVAL = Duration.ofMinutes(5);
    private final HashSet<NotificationTask> handledManually = new HashSet<>();

    public NotificationTaskScheduler(TaskScheduler scheduler, NotificationTaskRepository repository, NotificationSendStrategyRegistry sendStrategyRegistry) {
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
        NotificationTask[] tasks = this.REPOSITORY.retrieve(this.getScheduledUntilDateTime());

        for(NotificationTask task: tasks) {
            if (isHandledManually(task)) {
                handledManually.remove(task);
                continue;
            }

            scheduleInMemory(task);
        }
    }

    private boolean shouldScheduleInMemory(NotificationTask task) {
        return task.isScheduledBefore(
            this.getScheduledUntilDateTime()
        );
    }

    private void scheduleDirectlyInMemory(NotificationTask task) {
        this.handledManually.add(task);

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

        this.REPOSITORY.markCompleted(task);
    }

    private boolean isHandledManually(NotificationTask task) {
        return handledManually.contains(task);
    }

    private LocalDateTime getScheduledUntilDateTime() {
        return LocalDateTime.now().plus(this.RETRIEVE_INTERVAL);
    }
}
