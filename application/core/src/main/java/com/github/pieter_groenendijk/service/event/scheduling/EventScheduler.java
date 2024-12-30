package com.github.pieter_groenendijk.service.event.scheduling;

import com.github.pieter_groenendijk.utils.scheduling.TaskScheduler;
import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.service.event.detached.DetachedEvent;
import com.github.pieter_groenendijk.service.event.emitting.EventEmitterPool;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This event scheduler can, and expects to, schedule for the LONG TERM. Meaning at least a day usually.
 */
public class EventScheduler {
    private final TaskScheduler SCHEDULER;
    private final IEventRepository REPOSITORY;
    private final EventEmitterPool EMITTER_POOL;

    private final Duration RETRIEVE_INTERVAL = Duration.ofMinutes(5);

    public EventScheduler(
        TaskScheduler scheduler,
        IEventRepository repository,
        EventEmitterPool emitterPool
    ) {
        this.SCHEDULER = scheduler;
        this.REPOSITORY = repository;
        this.EMITTER_POOL = emitterPool;

        startSchedulingFromDatabase();
    }

    /**
     * Expects to schedule events that are at least the retrieve interval of this scheduler, otherwise a delay is
     * introduced.
     */
    public void schedule(DetachedEvent<?> event) {
        event.store();
    }

    private void startSchedulingFromDatabase() {
        this.SCHEDULER.scheduleRecurring(
            this::scheduleFromDatabase,
            this.RETRIEVE_INTERVAL
        );
    }

    private void scheduleFromDatabase() {
        this.scheduleInMemory(
            this.retrieveDueSoonEvents()
        );
    }

    private List<Event<?>> retrieveDueSoonEvents() {
        return this.REPOSITORY.retrieveUntil(
            this.getScheduledUntilDateTime()
        );
    }

    private void scheduleInMemory(List<Event<?>> events) {
        for(Event<?> event : events) {
            this.scheduleInMemory(event);
        }
    }

    private void scheduleInMemory(Event<?> event) {
        this.SCHEDULER.schedule(
            () -> executeEvent(event),
            event.getScheduledAt()
        );
    }

    private void executeEvent(Event<?> event) {
        this.EMITTER_POOL.emit(
            event.getType(),
            event.getAssociation()
        );
    }

    private LocalDateTime getScheduledUntilDateTime() {
        return LocalDateTime.now().plus(this.RETRIEVE_INTERVAL);
    }
}
