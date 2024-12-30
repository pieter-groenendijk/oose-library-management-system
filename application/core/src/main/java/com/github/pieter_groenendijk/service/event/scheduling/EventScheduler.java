package com.github.pieter_groenendijk.service.event.scheduling;

import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.service.event.emitting.EventEmitterPool;
import com.github.pieter_groenendijk.utils.scheduling.LongTermTaskScheduler;
import com.github.pieter_groenendijk.utils.scheduling.TaskScheduler;

import java.util.List;

public class EventScheduler extends LongTermTaskScheduler<Event<?>> {
    private final IEventRepository REPOSITORY;
    private final EventEmitterPool EMITTER_POOL;

    public EventScheduler(
        TaskScheduler scheduler,
        IEventRepository repository,
        EventEmitterPool eventEmitterPool
    ) {
        super(scheduler);

        this.REPOSITORY = repository;
        this.EMITTER_POOL = eventEmitterPool;
    }

    public EventScheduler(
        int amountOfThreads,
        IEventRepository repository,
        EventEmitterPool eventEmitterPool
    ) {
        super(amountOfThreads);

        this.REPOSITORY = repository;
        this.EMITTER_POOL = eventEmitterPool;
    }

    @Override
    protected void executeTask(Event<?> event) {
        this.EMITTER_POOL.emit(
            event.getType(),
            event.getAssociation()
        );
    }

    @Override
    protected List<Event<?>> retrieveDueSoonTasks() {
        return this.REPOSITORY.retrieveUntil(
            this.getScheduledUntilDateTime()
        );
    }
}