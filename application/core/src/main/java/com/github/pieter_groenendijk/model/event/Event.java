package com.github.pieter_groenendijk.model.event;

import com.github.pieter_groenendijk.model.Task;

// TODO: Make actual entity
// TODO: Use `scheduledAt` of super
public class Event<Association> extends Task {
    protected Association association;
    protected EventType type;

    public Event() {}

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
