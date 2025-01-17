package com.github.pieter_groenendijk.service.event.emitting;

import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.service.event.listener.IEventListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EventEmitterPoolTest {
    private EventEmitterPool pool;

    @BeforeEach
    void beforeEach() {
        pool = new EventEmitterPool();
    }

    @Test
    void testEmit_emittedForExistingEventType() {
        EventEmitter<String> emitter = mock(EventEmitter.class);
        EventType type = EventType.ALMOST_OVERDUE_LOAN;
        String context = "example";

        this.pool.add(type, emitter);

        this.pool.emit(type, context);

        verify(emitter, times(1)).emit(context);
    }

    @Test
    void testEmit_notEmittedForNonExistingEventType() {
        EventEmitter<String> emitter = mock(EventEmitter.class);
        EventType type = EventType.ALMOST_OVERDUE_LOAN;
        String context = "example";

        this.pool.emit(type, context);

        verifyNoInteractions(emitter);
    }

    @Test
    void testEmit_multipleAdded() {
        EventEmitter<String> stringEmitter = mock(EventEmitter.class);
        EventEmitter<Long> longEmitter = mock(EventEmitter.class);

        EventEmitter<Character> usedEmitter = mock(EventEmitter.class);
        EventType usedType = EventType.UNCOLLECTED_RESERVATION_EVENT;
        Character usedContext = Character.valueOf('k');

        this.pool.add(EventType.OVERDUE_LOAN, stringEmitter);
        this.pool.add(usedType, usedEmitter);
        this.pool.add(EventType.ALMOST_OVERDUE_LOAN, longEmitter);

        this.pool.emit(usedType, usedContext);

        verify(usedEmitter, times(1)).emit(usedContext);
        verifyNoInteractions(
            stringEmitter,
            longEmitter
        );
    }

    @Test
    void testEmit_multipleAdded_multipleEmitted() {
        EventEmitter<Long> longEmitter = mock(EventEmitter.class);

        EventEmitter<Character> firstUsedEmitter = mock(EventEmitter.class);
        EventType firstUsedType = EventType.UNCOLLECTED_RESERVATION_EVENT;
        Character firstUsedContext = Character.valueOf('k');

        EventEmitter<String> secondUsedEmitter = mock(EventEmitter.class);
        EventType secondUsedType = EventType.ALMOST_OVERDUE_LOAN;
        String secondUsedContext = "exampleeeee";

        this.pool.add(EventType.OVERDUE_LOAN, secondUsedEmitter);
        this.pool.add(firstUsedType, firstUsedEmitter);
        this.pool.add(secondUsedType, secondUsedEmitter);

        // First emit
        this.pool.emit(firstUsedType, firstUsedContext);

        verify(firstUsedEmitter, times(1)).emit(firstUsedContext);
        verifyNoInteractions(
            secondUsedEmitter,
            longEmitter
        );

        // Second emit
        this.pool.emit(secondUsedType, secondUsedContext);

        verifyNoMoreInteractions(
            firstUsedEmitter,
            longEmitter
        );
        verify(secondUsedEmitter, times(1)).emit(secondUsedContext);
    }

    @Test
    void testEmit_overwrittenEmitter() {
        EventEmitter<String> stringEmitter = mock(EventEmitter.class);

        EventEmitter<String> usedEmitter = mock(EventEmitter.class);
        EventType usedType = EventType.UNCOLLECTED_RESERVATION_EVENT;
        String usedContext = "example";

        this.pool.add(usedType, stringEmitter);
        this.pool.add(usedType, usedEmitter);

        this.pool.emit(usedType, usedContext);

        verifyNoInteractions(stringEmitter);
        verify(usedEmitter, times(1)).emit(usedContext);
    }

    @Test
    void testEmit_emitterIsRemoved() {
        EventEmitter<String> emitter = mock(EventEmitter.class);
        EventType type = EventType.ALMOST_OVERDUE_LOAN;
        String context = "example";

        this.pool.add(type, emitter);
        this.pool.remove(type);

        this.pool.emit(type, context);

        verifyNoInteractions(emitter);
    }


    @Test
    void testAttach_existingEmitter() {
        EventEmitter<String> emitter = mock(EventEmitter.class);
        EventType type = EventType.ALMOST_OVERDUE_LOAN;
        IEventListener<String> listener = (context) -> {};

        this.pool.add(type, emitter);

        this.pool.attach(type, listener);

        verify(emitter, times(1)).attach(listener);
    }

    @Test
    void testAttach_nonExistingEmitter() {
        EventType type = EventType.ALMOST_OVERDUE_LOAN;
        IEventListener<String> listener = (context) -> {};

        assertThrows(
            IllegalStateException.class,
            () -> this.pool.attach(type, listener)
        );
    }

    @Test
    void testAttach_multipleOfSameTypeAndDifferentListener() {
        EventEmitter<String> emitter = mock(EventEmitter.class);
        EventType type = EventType.ALMOST_OVERDUE_LOAN;
        IEventListener<String> firstListener = (context) -> {};
        IEventListener<String> secondListener = (context) -> {};
        IEventListener<String> thirdListener = (context) -> {};

        this.pool.add(type, emitter);

        this.pool.attach(type, firstListener);
        this.pool.attach(type, secondListener);
        this.pool.attach(type, thirdListener);

        verify(emitter, times(1)).attach(firstListener);
        verify(emitter, times(1)).attach(secondListener);
        verify(emitter, times(1)).attach(thirdListener);
    }

    @Test
    void testAttach_multipleOfSameTypeAndSameListener() {
        EventEmitter<String> emitter = mock(EventEmitter.class);
        EventType type = EventType.ALMOST_OVERDUE_LOAN;
        IEventListener<String> firstListener = (context) -> {};

        this.pool.add(type, emitter);

        this.pool.attach(type, firstListener);
        this.pool.attach(type, firstListener);
        this.pool.attach(type, firstListener);

        verify(emitter, times(3)).attach(firstListener);
    }

    @Test
    void testAttach_multipleOfDifferentTypeAndDifferentListener() {
        EventEmitter<String> emitter = mock(EventEmitter.class);
        EventEmitter<Long> secondEmitter = mock(EventEmitter.class);

        EventType type = EventType.ALMOST_OVERDUE_LOAN;
        EventType secondType = EventType.DAY_OVERDUE_LOAN;

        IEventListener<String> firstListener = (context) -> {};
        IEventListener<Long> secondListener = (context) -> {};

        this.pool.add(type, emitter);
        this.pool.add(secondType, secondEmitter);
        this.pool.add(type, emitter);

        this.pool.attach(type, firstListener);
        this.pool.attach(secondType, secondListener);
        this.pool.attach(type, firstListener);

        verify(emitter, times(2)).attach(firstListener);
        verify(secondEmitter, times(1)).attach(secondListener);
    }

    @Test
    void testAttach_multipleOfDifferentTypeAndSameListener() {
        EventEmitter<String> emitter = mock(EventEmitter.class);
        EventEmitter<String> secondEmitter = mock(EventEmitter.class);

        EventType type = EventType.ALMOST_OVERDUE_LOAN;
        EventType secondType = EventType.DAY_OVERDUE_LOAN;

        IEventListener<String> firstListener = (context) -> {};

        this.pool.add(type, emitter);
        this.pool.add(secondType, secondEmitter);
        this.pool.add(type, emitter);

        this.pool.attach(type, firstListener);
        this.pool.attach(secondType, firstListener);
        this.pool.attach(type, firstListener);

        verify(emitter, times(2)).attach(firstListener);
        verify(secondEmitter, times(1)).attach(firstListener);
    }


    @Test
    void testDetach_existingListener() {
        EventEmitter<String> emitter = mock(EventEmitter.class);
        EventType type = EventType.ALMOST_OVERDUE_LOAN;
        IEventListener<String> listener = (context) -> {};

        this.pool.add(type, emitter);
        this.pool.attach(type, listener);

        this.pool.detach(type, listener);

        verify(emitter, times(1)).detach(listener);
    }

    @Test
    void testDetach_nonExistentEmitter() {
        EventEmitter<String> emitter = mock(EventEmitter.class);
        EventType type = EventType.ALMOST_OVERDUE_LOAN;
        IEventListener<String> listener = (context) -> {};

        this.pool.detach(type, listener);

        verifyNoInteractions(emitter);
    }

    @Test
    void testDetach_nonExistentListener() {
        EventEmitter<String> emitter = mock(EventEmitter.class);
        EventType type = EventType.ALMOST_OVERDUE_LOAN;
        IEventListener<String> listener = (context) -> {};

        this.pool.add(type, emitter);

        this.pool.detach(type, listener);

        verify(emitter, times(1)).detach(listener);
    }

    @Test
    void testDetach_multipleDifferentListeners() {
        EventEmitter<String> emitter = mock(EventEmitter.class);
        EventType type = EventType.ALMOST_OVERDUE_LOAN;
        IEventListener<String> listener = (context) -> {};
        IEventListener<String> secondListener = (context) -> {};

        this.pool.add(type, emitter);
        this.pool.attach(type, listener);
        this.pool.attach(type, secondListener);

        this.pool.detach(type, listener);

        verify(emitter, times(1)).detach(listener);
        verify(emitter, times(0)).detach(secondListener);
    }

    @Test
    void testDetach_multipleSameListeners() {
        EventEmitter<String> emitter = mock(EventEmitter.class);
        EventType type = EventType.ALMOST_OVERDUE_LOAN;
        IEventListener<String> listener = (context) -> {};

        this.pool.add(type, emitter);
        this.pool.attach(type, listener);
        this.pool.attach(type, listener);

        this.pool.detach(type, listener);

        verify(emitter, times(1)).detach(listener);
    }

    @Test
    void testDetach_multipleEmittersSameListeners() {
        EventEmitter<String> emitter = mock(EventEmitter.class);
        EventType type = EventType.ALMOST_OVERDUE_LOAN;

        EventEmitter<String> secondEmitter = mock(EventEmitter.class);
        EventType secondType = EventType.DAY_OVERDUE_LOAN;

        IEventListener<String> listener = (context) -> {};

        this.pool.add(type, emitter);
        this.pool.add(secondType, secondEmitter);

        this.pool.attach(type, listener);
        this.pool.attach(secondType, listener);

        this.pool.detach(type, listener);

        verify(emitter, times(1)).detach(listener);
        verify(secondEmitter, times(0)).detach(listener);
    }

    @Test
    void testDetach_multipleEmittersDifferentListeners() {
        EventEmitter<String> emitter = mock(EventEmitter.class);
        EventType type = EventType.ALMOST_OVERDUE_LOAN;
        IEventListener<String> listener = (context) -> {};

        EventEmitter<String> secondEmitter = mock(EventEmitter.class);
        EventType secondType = EventType.DAY_OVERDUE_LOAN;
        IEventListener<String> secondListener = (context) -> {};


        this.pool.add(type, emitter);
        this.pool.add(secondType, secondEmitter);

        this.pool.attach(type, listener);
        this.pool.attach(secondType, secondListener);

        this.pool.detach(type, listener);

        verify(emitter, times(1)).detach(listener);
        verify(secondEmitter, times(0)).detach(secondListener);
    }

    @Test
    void testRemove_existingEmitter() {
        EventEmitter<String> emitter = mock(EventEmitter.class);
        EventType type = EventType.ALMOST_OVERDUE_LOAN;
        String context = "exmaple";

        this.pool.add(type, emitter);
        this.pool.remove(type);

        this.pool.emit(type, context);

        verifyNoInteractions(emitter);
    }

    @Test
    void testRemove_nonExistentEmitter() {
        this.pool.remove(EventType.ALMOST_OVERDUE_LOAN);
    }
}

