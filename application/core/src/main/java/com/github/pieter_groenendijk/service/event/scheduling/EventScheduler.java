package com.github.pieter_groenendijk.service.event.scheduling;

import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.repository.scheduling.ITaskRepository;
import com.github.pieter_groenendijk.service.event.emitting.EventEmitterPool;
import com.github.pieter_groenendijk.utils.scheduling.longterm.LongTermTaskScheduler;
import com.github.pieter_groenendijk.utils.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: Do something about the big constructors
@Component
public class EventScheduler extends LongTermTaskScheduler<Event<?>> {
    private final EventEmitterPool EMITTER_POOL;

    public EventScheduler(
        ITaskRepository<Event<?>> taskRepository,
        TaskScheduler scheduler,
        EventEmitterPool eventEmitterPool
    ) {
        super(
            taskRepository,
            scheduler
        );

        this.EMITTER_POOL = eventEmitterPool;
    }

    public EventScheduler(
        int amountOfThreads,
        ITaskRepository<Event<?>> taskRepository,
        EventEmitterPool eventEmitterPool
    ) {
        super(
            taskRepository,
            amountOfThreads
        );

        this.EMITTER_POOL = eventEmitterPool;
    }

    @Override
    protected void executeTask(Event<?> event) {
        this.EMITTER_POOL.emit(
            event.getType(),
            event.getAssociation()
        );
    }
}