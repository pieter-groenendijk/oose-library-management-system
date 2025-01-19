package com.github.pieter_groenendijk.model.scheduling;

import com.github.pieter_groenendijk.scheduling.TaskStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Task {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
        name = "scheduledAt",
        nullable = false
    )
    private LocalDateTime scheduledAt;

    @Enumerated(EnumType.STRING)
    @Column(
        name = "status",
        nullable = false,
        length = 50
    )
    private TaskStatus status;

    public Task() {}

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
