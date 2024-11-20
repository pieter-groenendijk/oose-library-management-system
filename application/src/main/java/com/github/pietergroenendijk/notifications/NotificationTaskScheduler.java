package com.github.pietergroenendijk.notifications;

import com.github.pietergroenendijk.TaskScheduler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;

public class NotificationTaskScheduler {
    private final TaskScheduler SCHEDULER;
    private final NotificationTaskRepository REPOSITORY;
    private final Duration RETRIEVE_INTERVAL = Duration.ofMinutes(5);
    private final HashSet<NotificationTask> handledManually = new HashSet<>();

    public NotificationTaskScheduler(TaskScheduler scheduler, NotificationTaskRepository repository) {
        this.SCHEDULER = scheduler;
        this.REPOSITORY = repository;

        startSchedulingFromDatabase();
    }

    public void schedule(NotificationTask task) {
        task.store();

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
        return task.isScheduledBefore(this.getScheduledUntilDateTime());
    }

    private void scheduleDirectlyInMemory(NotificationTask task) {
        this.handledManually.add(task);

        this.scheduleInMemory(task);
    }

    private void scheduleInMemory(NotificationTask task) {
        this.SCHEDULER.schedule(
            () -> this.executeTask(task),
            task.SCHEDULED_AT
        );
    }

    private void executeTask(NotificationTask task) {
        task.send();
        this.REPOSITORY.markCompleted(task);
    }

    private boolean isHandledManually(NotificationTask task) {
        return handledManually.contains(task);
    }

    private LocalDateTime getScheduledUntilDateTime() {
        return LocalDateTime.now().plus(this.RETRIEVE_INTERVAL);
    }
}
