package com.github.pieter_groenendijk.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDateTime;

@MappedSuperclass
public class Task {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
        name = "scheduledAt",
        nullable = false
    )
    private LocalDateTime scheduledAt;

    public Task() {}

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }
}
