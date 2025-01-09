package com.github.pieter_groenendijk.service.event.listener;

import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.service.event.emitting.EventEmitterPool;

public abstract class EventListener<Context> implements IEventListener<Context> {
    private final EventType TYPE;

    protected EventListener(EventType type) {
        this.TYPE = type;
    }

    public void attachTo(EventEmitterPool pool) {
        pool.attach(this.TYPE, this);
    }

    @Override
    public abstract void react(Context context);
}
