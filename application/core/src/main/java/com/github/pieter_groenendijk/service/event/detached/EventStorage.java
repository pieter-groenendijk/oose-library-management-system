package com.github.pieter_groenendijk.service.event.detached;

public interface EventStorage<AssociatedEvent> {
    void store(AssociatedEvent event);
}
