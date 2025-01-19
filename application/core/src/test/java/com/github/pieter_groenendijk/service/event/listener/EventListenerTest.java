package com.github.pieter_groenendijk.service.event.listener;

import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.service.event.emitting.EventEmitterPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class EventListenerTest {
    @Mock
    private EventType eventType;

    private EventListener<Long> eventListener;

    @BeforeEach
    void beforeEach() {
        openMocks(this);
    }

    @Test
    void testAttachTo() {
        this.eventListener = new EventListener<Long>(mock(EventType.class)) {
            @Override
            protected void tryReact(Long aLong) throws Exception {}
        };

        this.eventListener.attachTo(mock(EventEmitterPool.class));
    }

    @Test
    void testAttachTo_attached() {
        EventType type = mock(EventType.class);
        EventEmitterPool emitterPool = mock(EventEmitterPool.class);
        this.eventListener = new EventListener<Long>(type) {
            @Override
            protected void tryReact(Long aLong) throws Exception {}
        };

        this.eventListener.attachTo(emitterPool);

        verify(emitterPool, times(1)).attach(type, this.eventListener);
    }

    @Test
    void testReact() {
        this.eventListener = new EventListener<Long>(mock(EventType.class)) {
            @Override
            protected void tryReact(Long aLong) throws Exception {}
        };

        this.eventListener.react(123L);
    }

    @Test
    void testReact_tryReactCalled() throws Exception {
        Long context = 1L;

        this.eventListener = spy(
            new EventListener<Long>(mock(EventType.class)) {
                @Override
                protected void tryReact(Long aLong) {}
            }
        );

        this.eventListener.react(context);

        verify(this.eventListener, times(1)).tryReact(context);
    }

    @Test
    void testReact_tryReactWithException() {
        Exception thrownException = new Exception();

        this.eventListener = spy(
            new EventListener<Long>(mock(EventType.class)) {
                @Override
                protected void tryReact(Long aLong) throws Exception {
                    throw thrownException;
                }
            }
        );

        this.eventListener.react(1213123L);

        // TODO: We should check the actual logging in the future. Testing results of implementation, not the implementation self.
        verify(this.eventListener, times(1)).handleReactException(thrownException);
    }
}
