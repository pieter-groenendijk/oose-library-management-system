package com.github.pieter_groenendijk.service.product;

import com.github.pieter_groenendijk.service.event.emitting.EventEmitterPool;
import com.github.pieter_groenendijk.service.event.listener.EventListener;
import org.springframework.stereotype.Service;


public class EventPoolListener {
    protected final EventEmitterPool EVENT_EMITTER_POOL;

    public EventPoolListener(
        EventEmitterPool eventEmitterPool,
        EventListener<?>[] listeners
    ) {
        EVENT_EMITTER_POOL = eventEmitterPool;

        this.startListening(listeners);
    }

    private void startListening(EventListener<?>[] listeners) {
        for (EventListener<?> listener : listeners) {
            listener.attachTo(
                this.EVENT_EMITTER_POOL
            );
        }
    }
}
