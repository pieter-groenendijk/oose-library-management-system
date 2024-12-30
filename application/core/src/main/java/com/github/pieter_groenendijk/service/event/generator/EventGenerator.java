package com.github.pieter_groenendijk.service.event.generator;

import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.service.event.detached.EventStorage;

import java.time.LocalDateTime;

/**
 * A generic is used to specify what the DetachedEvent will wrap. This generic exists because:
 * - We want the event types to follow Liskov's substitution principle. We can't make a separate entity that holds a reference to the event
 * and the association for this reason. A side effect is that we also lose control over the db structure; we can't choose
 * an inheritance type.
 * - For that reason we need a subclass that extends Event (generics are removed at runtime, so we need a subclass). To
 * be able to correctly store this subclass though it needs to be known to the ORM.
 * - For that reason the generator also needs to know what it will generate. Since it passes the mechanism to store the
 * generated (wrapped) event.
 */
public abstract class EventGenerator<Association, AssociatedEvent extends Event<Association>> {
    protected final IEventRepository REPOSITORY;

    private final EventType TYPE;

    protected EventGenerator(IEventRepository repository, EventType type) {
        this.REPOSITORY = repository;
        this.TYPE = type;
    }

    public final void generate(Association association) {
        AssociatedEvent event = this.generateEvent(association);
    }

    private AssociatedEvent generateEvent(Association association) {
        AssociatedEvent event = this.generateEmptyEvent();

        event.setScheduledAt(
            this.determineScheduledDateTime(association)
        );
        event.setType(this.TYPE);
        event.setAssociation(association);

        return event;
    }

    protected abstract AssociatedEvent generateEmptyEvent();

    protected abstract LocalDateTime determineScheduledDateTime(Association association);

    protected abstract EventStorage<AssociatedEvent> generateEventStorage();
}
