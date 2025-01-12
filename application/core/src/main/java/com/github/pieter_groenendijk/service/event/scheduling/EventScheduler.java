package com.github.pieter_groenendijk.service.event.scheduling;

import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.repository.scheduling.ITaskRepository;
import com.github.pieter_groenendijk.service.event.emitting.EventEmitterPool;
import com.github.pieter_groenendijk.utils.scheduling.longterm.LongTermTaskScheduler;
import com.github.pieter_groenendijk.utils.scheduling.TaskScheduler;

import java.util.List;

// TODO: Do something about the big constructors
public class EventScheduler extends LongTermTaskScheduler<Event<?>> {
    private final IEventRepository REPOSITORY;
    private final EventEmitterPool EMITTER_POOL;

    public EventScheduler(
        ITaskRepository taskRepository,
        TaskScheduler scheduler,
        IEventRepository repository,
        EventEmitterPool eventEmitterPool
    ) {
        super(
            taskRepository,
            scheduler
        );

        this.REPOSITORY = repository;
        this.EMITTER_POOL = eventEmitterPool;
    }

    public EventScheduler(
        int amountOfThreads,
        ITaskRepository taskRepository,
        IEventRepository repository,
        EventEmitterPool eventEmitterPool
    ) {
        super(
            taskRepository,
            amountOfThreads
        );

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