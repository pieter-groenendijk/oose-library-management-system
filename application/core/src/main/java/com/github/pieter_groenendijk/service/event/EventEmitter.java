package com.github.pieter_groenendijk.service.event;

import java.util.ArrayList;

public abstract class EventEmitter<T> {
    private final ArrayList<EventRunnable<T>> RUNNABLES;

    protected EventEmitter() {
        RUNNABLES = new ArrayList<>();
    }

    public void attach(EventRunnable<T> runnable) {
        this.RUNNABLES.add(runnable);
    }

    /**
     * Relatively unoptimized action. It's not expected that it's called frequently.
     */
    public void detach(EventRunnable<T> runnable) {
        this.RUNNABLES.remove(runnable);
    }

    public void emit(T context) {
        for (EventRunnable<T> runnable : this.RUNNABLES) {
            runnable.run(context);
        }
    }
}
