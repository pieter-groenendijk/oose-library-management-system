package com.github.pieter_groenendijk.model.event;

import java.time.LocalDateTime;

// TODO: Make actual entity
public class Event<Association> {
    protected Association association;
    protected LocalDateTime scheduledAt;
    protected EventType type;

    public Event() {}

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }
}
