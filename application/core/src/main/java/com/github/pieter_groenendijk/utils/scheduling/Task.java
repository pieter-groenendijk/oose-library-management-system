package com.github.pieter_groenendijk.utils.scheduling;

import java.time.LocalDateTime;

public class Task {
    private LocalDateTime scheduledAt;

    public Task() {}

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }
}
