package com.github.pieter_groenendijk.service.event.detached;

import com.github.pieter_groenendijk.model.event.Event;

public interface EventStorage<T> {
    void store(Event<T> event);
}
