package com.github.pietergroenendijk;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {
    private final ScheduledExecutorService SCHEDULER;

    public TaskScheduler(int threadAmount) {
        this.SCHEDULER = Executors.newScheduledThreadPool(threadAmount);
    }

    public void schedule(Runnable task, LocalDateTime scheduledAt) {
        this.SCHEDULER.schedule(
            task,
            calculateRelativeScheduleTime(scheduledAt),
            TimeUnit.MILLISECONDS
        );
    }

    private long calculateRelativeScheduleTime(LocalDateTime scheduledAt) {
        return Duration.between(
            LocalDateTime.now(),
            scheduledAt
        ).toMillis();
    }
}
