package com.github.pieter_groenendijk.service.event;

public interface EventRunnable<T> {
    void run(T context);
}
