package com.github.pieter_groenendijk.service.event.emitting;

import com.github.pieter_groenendijk.service.event.listener.IEventListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class EventEmitter<Context> {
    private final ArrayList<IEventListener<Context>> RUNNABLES;

    protected EventEmitter() {
        RUNNABLES = new ArrayList<>();
    }

    public void attach(@NotNull IEventListener<Context> runnable) {
        this.RUNNABLES.add(runnable);
    }

    /**
     * Relatively unoptimized action. It's not expected that it's called frequently.
     */
    public void detach(@NotNull IEventListener<Context> runnable) {
        this.RUNNABLES.remove(runnable);
    }

    public void emit(Context context) {
        for (IEventListener<Context> runnable : this.RUNNABLES) {
            runnable.react(context);
        }
    }
}
