package com.github.pieter_groenendijk.service.event.listener;

import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.service.event.emitting.EventEmitterPool;
import org.jetbrains.annotations.NotNull;

public abstract class EventListener<Context> implements IEventListener<Context> {
    private final EventType TYPE;

    protected EventListener(@NotNull EventType type) {
        this.TYPE = type;
    }

    public final void attachTo(@NotNull EventEmitterPool pool) {
        pool.attach(this.TYPE, this);
    }

    @Override
    public void react(Context context) {
        try {
            this.tryReact(context);
        } catch (Exception exception) {
            this.handleReactException(exception);
        }
    }

    protected abstract void tryReact(Context context) throws Exception;

    protected void handleReactException(@NotNull Exception exception) {
        // TODO: Logging
    }
}
