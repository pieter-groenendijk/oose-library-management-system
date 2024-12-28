package com.github.pieter_groenendijk.service.event.detached;

import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.repository.event.IEventRepository;

/**
 * An event that hasn't been processed with the scheduler yet.
 */
public class DetachedEvent {
    private final Event EVENT;

    public DetachedEvent(Event event) {
        this.EVENT = event;
    }

    public
}