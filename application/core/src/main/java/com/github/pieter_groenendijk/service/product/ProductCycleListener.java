package com.github.pieter_groenendijk.service.product;

import com.github.pieter_groenendijk.service.event.emitting.EventEmitterPool;

public abstract class ProductCycleListener {
    protected final EventEmitterPool EVENT_EMITTER_POOL;

    public ProductCycleListener(EventEmitterPool eventEmitterPool) {
        EVENT_EMITTER_POOL = eventEmitterPool;
    }

    public abstract void startListening();
}
