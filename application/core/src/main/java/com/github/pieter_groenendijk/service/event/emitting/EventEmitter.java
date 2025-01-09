package com.github.pieter_groenendijk.service.event.emitting;

import com.github.pieter_groenendijk.service.event.listener.IEventListener;

import java.util.ArrayList;

public abstract class EventEmitter<Context> {
    private final ArrayList<IEventListener<Context>> RUNNABLES;

    protected EventEmitter() {
        RUNNABLES = new ArrayList<>();
    }

    public void attach(IEventListener<Context> runnable) {
        this.RUNNABLES.add(runnable);
    }

    /**
     * Relatively unoptimized action. It's not expected that it's called frequently.
     */
    public void detach(IEventListener<Context> runnable) {
        this.RUNNABLES.remove(runnable);
    }

    public void emit(Context context) {
        for (IEventListener<Context> runnable : this.RUNNABLES) {
            runnable.react(context);
        }
    }
}
