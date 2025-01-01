package com.github.pieter_groenendijk.service.event.emitting;

public interface EventRunnable<T> {
    void run(T context);
}
