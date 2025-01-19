package com.github.pieter_groenendijk.service.event.scheduling;

import com.github.pieter_groenendijk.model.event.Event;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.repository.scheduling.ITaskRepository;
import com.github.pieter_groenendijk.scheduling.TaskScheduler;
import com.github.pieter_groenendijk.service.event.emitting.EventEmitterPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class EventSchedulerTest {
    @Mock
    private ITaskRepository<Event<?>> taskRepository;
    @Mock
    private EventEmitterPool emitterPool;
    @Mock
    private TaskScheduler taskScheduler;


    private EventScheduler eventScheduler;

    @BeforeEach
    void beforeEach() {
        openMocks(this);

        this.eventScheduler = new EventScheduler(
            taskRepository,
            taskScheduler,
            emitterPool
        );
    }

    @Test
    void testExecuteTask() {
        this.eventScheduler.executeTask(mock(Event.class));
    }

    // TODO: Very integration specific, something we would want to prevent mostly.
    @Test
    void testExecuteTask_emitInPool() {
        EventType eventType = mock(EventType.class);
        String association = "example";

        Event<String> event = mock(Event.class);
        when(event.getAssociation()).thenReturn(association);
        when(event.getType()).thenReturn(eventType);

        this.eventScheduler.executeTask(event);

        verify(this.emitterPool, times(1)).emit(eventType, association);
    }

    @Test
    void testExecuteTask_multipleCalled() {
        EventType eventType = mock(EventType.class);
        String association = "example";

        Event<String> event = mock(Event.class);
        when(event.getAssociation()).thenReturn(association);
        when(event.getType()).thenReturn(eventType);

        this.eventScheduler.executeTask(event);
        this.eventScheduler.executeTask(event);

        verify(this.emitterPool, times(2)).emit(eventType, association);
    }
}
