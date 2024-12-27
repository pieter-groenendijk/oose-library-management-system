package com.github.pieter_groenendijk.service.event.detached;

import com.github.pieter_groenendijk.repository.event.IEventRepository;

/**
 * An event that hasn't been processed with the scheduler yet.
 */
public abstract class DetachedEvent {
    public abstract void store(IEventRepository repository);
}
