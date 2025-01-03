package com.github.pieter_groenendijk.service.event.emitting;

import java.util.ArrayList;

public abstract class EventEmitter<Context> {
    private final ArrayList<EventRunnable<Context>> RUNNABLES;

    protected EventEmitter() {
        RUNNABLES = new ArrayList<>();
    }

    public void attach(EventRunnable<Context> runnable) {
        this.RUNNABLES.add(runnable);
    }

    /**
     * Relatively unoptimized action. It's not expected that it's called frequently.
     */
    public void detach(EventRunnable<Context> runnable) {
        this.RUNNABLES.remove(runnable);
    }

    public void emit(Context context) {
        for (EventRunnable<Context> runnable : this.RUNNABLES) {
            runnable.run(context);
        }
    }
}
