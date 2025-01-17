package com.github.pieter_groenendijk.service.event.emitting;

import com.github.pieter_groenendijk.model.event.EventType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.verification.VerificationMode;

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

        verify(emitter).emit(context);
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

        verify(usedEmitter).emit(usedContext);
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

        verify(firstUsedEmitter).emit(firstUsedContext);
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
        verify(secondUsedEmitter).emit(secondUsedContext);
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
        verify(usedEmitter).emit(usedContext);
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
}
