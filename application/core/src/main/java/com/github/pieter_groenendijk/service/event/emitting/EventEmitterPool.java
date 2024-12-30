package com.github.pieter_groenendijk.service.event.emitting;

import com.github.pieter_groenendijk.model.event.EventType;

import java.util.HashMap;

/**
 * This class adds a centralized way to emit events. Handy for classes that emit multiple events.
 */
public class EventEmitterPool {
    private final HashMap<EventType, EventEmitter<?>> EMITTERS;

    public EventEmitterPool() {
        this.EMITTERS = new HashMap<>();
    }

    /**
     * Misuse of adding the wrong EventEmitter with the wrong EventType will cause runtime errors!
     */
    public void add(EventType eventType, EventEmitter<?> eventEmitter) {
        this.EMITTERS.put(eventType, eventEmitter);
    }

    public void remove(EventType eventType) {
        this.EMITTERS.remove(eventType);
    }

    /**
     * May throw an exception if there is EventEmitter found.
     */
    public <T> void emit(EventType type, T context) {
        EventEmitter<T> emitter = this.get(type);

        emitter.emit(context);
    }

    @SuppressWarnings("unchecked") // We assume people haven't mismatched at the .add() method.
    private <T> EventEmitter<T> get(EventType eventType) {
        return (EventEmitter<T>) this.EMITTERS.get(eventType);
    }
}
