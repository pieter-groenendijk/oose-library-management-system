package com.github.pieter_groenendijk.utils.scheduling;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public abstract class LongTermTaskScheduler<T extends Task> {
    private final TaskScheduler SCHEDULER; // We don't use inheritance because we want to determine the interface, and we may want to share the underlying scheduler between multiple subclasses.

    // TODO: Allow customization in constructor?
    private final Duration RETRIEVE_INTERVAL = Duration.ofMinutes(5);

    public LongTermTaskScheduler(TaskScheduler scheduler) {
        this.SCHEDULER = scheduler;
    }

    public LongTermTaskScheduler(int amountOfThreads) {
        this.SCHEDULER = new TaskScheduler(amountOfThreads);
    }

    /**
     * Expects to schedule events that are at least the retrieve interval of this scheduler, otherwise a delay is experienced.
     */
    public void schedule(DetachedTask<? extends T> task) {
        task.store();
    }

    protected final LocalDateTime getScheduledUntilDateTime() {
        return LocalDateTime.now().plus(this.RETRIEVE_INTERVAL);
    }

    // TODO: Maybe move this responsibility to the actual task
    protected abstract void executeTask(T task);

    protected abstract List<T> retrieveDueSoonTasks();

    private void startSchedulingFromDatabase() {
        this.SCHEDULER.scheduleRecurring(
            this::scheduleFromDatabase,
            this.RETRIEVE_INTERVAL
        );
    }

    private void scheduleFromDatabase() {
        this.scheduleInMemory(
            this.retrieveDueSoonTasks()
        );
    }

    private void scheduleInMemory(List<T> tasks) {
        for(T task: tasks) {
            this.scheduleInMemory(task);
        }
    }

    private void scheduleInMemory(T task) {
        this.SCHEDULER.schedule(
            () -> executeTask(task),
            task.getScheduledAt()
        );
    }
}
