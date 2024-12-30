package com.github.pieter_groenendijk.service.event.detached;

import com.github.pieter_groenendijk.model.event.Event;

/**
 * This class wraps around an event to provide a consistent call signature to store the object, without having to know what
 * specific subclass of Event it is and how it should be stored.
 */
public class DetachedEvent<AssociatedEvent extends Event<?>> {
    private final AssociatedEvent EVENT;
    private final EventStorage<AssociatedEvent> STORAGE;

    public DetachedEvent(
        AssociatedEvent event,
        EventStorage<AssociatedEvent> storage
    ) {
        this.EVENT = event;
        this.STORAGE = storage;
    }

    public void store() {
        this.STORAGE.store(
            this.EVENT
        );
    }
}