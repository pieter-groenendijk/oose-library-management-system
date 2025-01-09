package com.github.pieter_groenendijk.service.loan.event.listener;

import com.github.pieter_groenendijk.service.event.emitting.EventEmitterPool;
import com.github.pieter_groenendijk.service.event.listener.EventListener;
import com.github.pieter_groenendijk.service.product.EventPoolListener;

public class LoanCycleListener extends EventPoolListener {
    public LoanCycleListener(EventEmitterPool eventEmitterPool) {
        super(
            eventEmitterPool,
            new EventListener[]{
                new AlmostOverdueEventListener(),
                new OverdueEventListener(),
                new DayOverdueEventListener()
            }
        );
    }
}
