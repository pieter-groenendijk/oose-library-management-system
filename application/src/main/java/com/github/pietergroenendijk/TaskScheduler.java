package com.github.pietergroenendijk;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {
    private static final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    private final ScheduledExecutorService SCHEDULER;

    public TaskScheduler(int threadAmount) {
        this.SCHEDULER = Executors.newScheduledThreadPool(threadAmount);
    }

    public void schedule(Runnable task, LocalDateTime scheduledAt) {
        this.SCHEDULER.schedule(
            task,
            calculateRelativeScheduleTime(scheduledAt),
            TaskScheduler.TIME_UNIT
        );
    }

    public void scheduleRecurring(Runnable task, Duration interval) {
        long intervalInTimeUnit = this.durationToMillis(interval);

        this.SCHEDULER.scheduleWithFixedDelay(
            task,
            intervalInTimeUnit,
            intervalInTimeUnit,
            TaskScheduler.TIME_UNIT
        );
    }

    private long calculateRelativeScheduleTime(LocalDateTime scheduledAt) {
        return this.durationToMillis(
            Duration.between(
                LocalDateTime.now(),
                scheduledAt
            )
        );
    }

    private long durationToMillis(Duration duration) {
        return duration.toMillis();
    }
}
