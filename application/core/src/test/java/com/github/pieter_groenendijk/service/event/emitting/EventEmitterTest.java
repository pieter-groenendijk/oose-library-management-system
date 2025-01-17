package com.github.pieter_groenendijk.service.event.emitting;

import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.service.event.listener.IEventListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class EventEmitterTest {
    private EventEmitter<String> emitter;

    private IEventListener<String> listener;
    private String context;

    @BeforeEach
    void beforeEach() {
        this.emitter = new EventEmitter<>() {
        };
        this.listener = mock(IEventListener.class);
        this.context = "example";
    }

    @Test
    void testEmit() {
        this.emitter.emit(this.context);
    }

    @Test
    void testDetach() {
        this.emitter.detach(this.listener);
    }

    @Test
    void testAttach() {
        this.emitter.attach(listener);
    }


    @Test
    void testEmit_afterSingleAttached() {
        this.emitter.attach(listener);

        this.emitter.emit(this.context);

        verify(listener, times(1)).react(this.context);
    }

    @Test
    void testEmit_afterMultipleDifferentAttached() {
        IEventListener<String> secondListener = mock(IEventListener.class);
        IEventListener<String> thirdListener = mock(IEventListener.class);

        this.emitter.attach(this.listener);
        this.emitter.attach(secondListener);
        this.emitter.attach(thirdListener);

        this.emitter.emit(this.context);

        verify(this.listener, times(1)).react(this.context);
        verify(secondListener, times(1)).react(this.context);
    }

    @Test
    void testEmit_afterMultipleSameAttached() {
        this.emitter.attach(this.listener);
        this.emitter.attach(this.listener);
        this.emitter.attach(this.listener);
        this.emitter.attach(this.listener);

        this.emitter.emit(this.context);

        verify(this.listener, times(4)).react(this.context);
    }

    @Test
    void testEmit_afterSingleDetached() {
        this.emitter.attach(this.listener);

        this.emitter.detach(this.listener);

        this.emitter.emit(this.context);

        verifyNoInteractions(this.listener);
    }

    @Test
    void testEmit_afterSingleDetachedOfMultipleDifferent() {
        IEventListener<String> secondListener = mock(IEventListener.class);
        IEventListener<String> thirdListener = mock(IEventListener.class);

        this.emitter.attach(this.listener);
        this.emitter.attach(secondListener);
        this.emitter.attach(thirdListener);

        this.emitter.detach(secondListener);

        this.emitter.emit(this.context);

        verifyNoInteractions(secondListener);
        verify(this.listener, times(1)).react(this.context);
        verify(thirdListener, times(1)).react(this.context);
    }

    @Test
    void testEmit_afterSingleDetachedOfMultipleSame() {
        this.emitter.attach(this.listener);
        this.emitter.attach(this.listener);
        this.emitter.attach(this.listener);

        this.emitter.detach(this.listener);

        this.emitter.emit(this.context);

        verify(this.listener, times(2)).react(context);
    }

    @Test
    void testEmit_afterMultipleDetachedOfMultiple() {
        IEventListener<String> secondListener = mock(IEventListener.class);
        IEventListener<String> thirdListener = mock(IEventListener.class);

        this.emitter.attach(this.listener);
        this.emitter.attach(this.listener);
        this.emitter.attach(secondListener);
        this.emitter.attach(this.listener);
        this.emitter.attach(thirdListener);
        this.emitter.attach(thirdListener);

        this.emitter.detach(this.listener);
        this.emitter.detach(secondListener);
        this.emitter.detach(thirdListener);

        this.emitter.emit(this.context);

        verify(this.listener, times(2)).react(this.context);
        verify(secondListener, times(0)).react(this.context);
        verify(thirdListener, times(1)).react(this.context);
    }
}
