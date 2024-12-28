package com.github.pieter_groenendijk.service.event.generator;

import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.service.event.detached.EventStorage;

import java.time.LocalDateTime;

public abstract class EventGenerator<T> {
    protected final IEventRepository REPOSITORY;

    private final EventType TYPE;

    protected EventGenerator(IEventRepository repository, EventType type) {
        this.REPOSITORY = repository;
        this.TYPE = type;
    }

//    public final DetachedEvent generate(T context) {
//    }

    protected final Event<T> generateEvent(T context) {
        Event<T> event = new Event();
        event.setType(this.TYPE);
        event.setScheduledAt(this.determineScheduledDateTime(context));

        return event;
    }

    protected abstract LocalDateTime determineScheduledDateTime(T context);

    protected abstract EventStorage<T> generateEventStorage(T context);
}
