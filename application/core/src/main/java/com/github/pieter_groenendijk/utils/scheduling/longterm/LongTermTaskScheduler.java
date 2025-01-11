package com.github.pieter_groenendijk.utils.scheduling.longterm;

import com.github.pieter_groenendijk.model.scheduling.Task;
import com.github.pieter_groenendijk.repository.scheduling.ITaskRepository;
import com.github.pieter_groenendijk.utils.scheduling.TaskScheduler;
import com.github.pieter_groenendijk.utils.scheduling.TaskStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public abstract class LongTermTaskScheduler<T extends Task> {
    private final ITaskRepository REPOSITORY;
    private final TaskScheduler SCHEDULER; // We don't use inheritance because we want to determine the interface, and we may want to share the underlying scheduler between multiple subclasses.

    // TODO: Allow customization in constructor?
    private final Duration RETRIEVE_INTERVAL = Duration.ofMinutes(5);

    public LongTermTaskScheduler(ITaskRepository repository, TaskScheduler scheduler) {
        this.REPOSITORY = repository;
        this.SCHEDULER = scheduler;
    }

    public LongTermTaskScheduler(ITaskRepository repository, int amountOfThreads) {
        this.REPOSITORY = repository;
        this.SCHEDULER = new TaskScheduler(amountOfThreads);
    }

    /**
     * Expects to schedule events that are at least the retrieve interval of this scheduler, otherwise a delay is experienced.
     */
    public void schedule(DetachedTask<? extends T> task) {
        task.store();
    }

    /**
     * A task that is already scheduled in memory can't be cancelled anymore.
     */
    public void cancel(T task) throws Exception {
        try {
            this.tryCancel(task);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private void tryCancel(T task) throws Exception {
        this.REPOSITORY.updateStatus(
            task,
            TaskStatus.CANCELLED
        );
    }

    protected final LocalDateTime getScheduledUntilDateTime() {
        return LocalDateTime.now()
            .plus(this.RETRIEVE_INTERVAL);
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

    private void execute(T task) {
        try {
            this.tryExecute(task);
        } catch (Exception exception) {
            // TODO: Implement logging for errors
            throw new RuntimeException(exception);
        }
    }

    private void tryExecute(T task) throws Exception {
        this.executeTask(task);
        this.markTaskCompleted(task);
    }

    private void markTaskCompleted(T task) throws Exception {
        this.REPOSITORY.updateStatus(
            task,
            TaskStatus.COMPLETED
        );
    }
}
