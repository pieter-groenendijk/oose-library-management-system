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

    public <T> void attach(EventType type, EventRunnable<T> runnable) {
        EventEmitter<T> emitter = this.get(type);

        if (emitter == null) return;

        emitter.attach(runnable);
    }

    /**
     * Relatively unoptimized, only use as last resort.
     */
    public <T> void detach(EventType type, EventRunnable<T> runnable) {
        EventEmitter<T> emitter = this.get(type);

        if (emitter == null) return;

        emitter.detach(runnable);
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
     * Fails silently if the emitter is not found in the pool.
     */
    public <T> void emit(EventType type, T context) {
        EventEmitter<T> emitter = this.get(type);

        if (emitter == null) return;

        emitter.emit(context);
    }

    @SuppressWarnings("unchecked") // We assume people haven't mismatched at the .add() method.
    private <T> EventEmitter<T> get(EventType eventType) {
        return (EventEmitter<T>) this.EMITTERS.get(eventType);
    }
}
