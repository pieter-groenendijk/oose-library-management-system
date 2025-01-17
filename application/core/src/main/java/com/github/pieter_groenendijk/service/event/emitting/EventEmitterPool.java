package com.github.pieter_groenendijk.service.event.emitting;

import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.service.event.listener.IEventListener;
import jakarta.annotation.Nonnull;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * This class adds a centralized way to emit events. Handy for classes that emit multiple events.
 */
public class EventEmitterPool {
    private final HashMap<EventType, EventEmitter<?>> EMITTERS;

    public EventEmitterPool() {
        this.EMITTERS = new HashMap<>();
    }

    public <T> void attach(
        @NotNull EventType type,
        @NotNull IEventListener<T> runnable
    ) {
        EventEmitter<T> emitter = this.get(type);

        if (emitter == null) {
            throw new IllegalStateException("EventEmitter for event type " + type + " not found in the pool");
        }

        emitter.attach(runnable);
    }

    /**
     * Relatively unoptimized, only use as last resort.
     */
    public <T> void detach(
        @NotNull EventType type,
        @NotNull IEventListener<T> runnable
    ) {
        EventEmitter<T> emitter = this.get(type);

        if (emitter == null) return;

        emitter.detach(runnable);
    }

    /**
     * Misuse of adding the wrong EventEmitter with the wrong EventType will cause runtime errors!
     */
    public void add(
        @NotNull EventType eventType,
        @NotNull EventEmitter<?> eventEmitter
    ) {
        this.EMITTERS.put(eventType, eventEmitter);
    }

    public void remove(@NotNull EventType eventType) {
        this.EMITTERS.remove(eventType);
    }

    /**
     * Fails silently if the emitter is not found in the pool.
     */
    public <T> void emit(@NotNull EventType type, T context) {
        EventEmitter<T> emitter = this.get(type);

        if (emitter == null) return;

        emitter.emit(context);
    }

    @SuppressWarnings("unchecked") // We assume people haven't mismatched at the .add() method.
    private <T> EventEmitter<T> get(@NotNull EventType eventType) {
        return (EventEmitter<T>) this.EMITTERS.get(eventType);
    }
}
