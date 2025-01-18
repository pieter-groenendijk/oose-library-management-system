package com.github.pieter_groenendijk.service.event.scheduling;

import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.repository.scheduling.ITaskRepository;
import com.github.pieter_groenendijk.service.event.emitting.EventEmitterPool;
import com.github.pieter_groenendijk.utils.scheduling.longterm.LongTermTaskScheduler;
import com.github.pieter_groenendijk.utils.scheduling.TaskScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// TODO: Do something about the big constructors
public class EventScheduler extends LongTermTaskScheduler<Event<?>> {
    private final EventEmitterPool EMITTER_POOL;

    public EventScheduler(
        @NotNull ITaskRepository<Event<?>> taskRepository,
        @NotNull TaskScheduler scheduler,
        @NotNull EventEmitterPool eventEmitterPool
    ) {
        super(
            taskRepository,
            scheduler
        );

        this.EMITTER_POOL = eventEmitterPool;
    }

    public EventScheduler(
        int amountOfThreads,
        @NotNull ITaskRepository<Event<?>> taskRepository,
        @NotNull EventEmitterPool eventEmitterPool
    ) {
        super(
            taskRepository,
            amountOfThreads
        );

        this.EMITTER_POOL = eventEmitterPool;
    }

    @Override
    protected void executeTask(@NotNull Event<?> event) {
        this.EMITTER_POOL.emit(
            event.getType(),
            event.getAssociation()
        );
    }
}