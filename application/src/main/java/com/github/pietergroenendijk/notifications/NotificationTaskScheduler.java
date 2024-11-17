package com.github.pietergroenendijk.notifications;

import com.github.pietergroenendijk.TaskScheduler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;

public class NotificationTaskScheduler {
    private final TaskScheduler SCHEDULER;
    private final Duration RETRIEVE_INTERVAL;
    private HashSet<NotificationTask> handledManually;

    private final NotificationTaskRepository REPOSITORY;

    public NotificationTaskScheduler(TaskScheduler scheduler, Duration retrieveInterval, NotificationTaskRepository repository) {
        this.SCHEDULER = scheduler;
        this.RETRIEVE_INTERVAL = retrieveInterval;
        this.REPOSITORY = repository;
    }

    public void schedule(NotificationTask task) {
        this.REPOSITORY.store(task);

        if (shouldScheduleInMemory(task)) {
            scheduleDirectlyInMemory(task);
        }
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
            task::send,
            task.scheduledDateTime()
        );
    }

    private boolean isHandledManually(NotificationTask task) {
        return handledManually.contains(task);
    }

    private LocalDateTime getScheduledUntilDateTime() {
        return LocalDateTime.now().plus(this.RETRIEVE_INTERVAL);
    }
}
