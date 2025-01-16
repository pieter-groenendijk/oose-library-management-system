package com.github.pieter_groenendijk.service.loan.event.listener;

import com.github.pieter_groenendijk.service.event.emitting.EventEmitterPool;
import com.github.pieter_groenendijk.service.event.listener.EventListener;
import com.github.pieter_groenendijk.service.fine.FineProcessor;
import com.github.pieter_groenendijk.service.loan.event.scheduling.LoanEventScheduler;
import com.github.pieter_groenendijk.service.loan.fine.LoanFineService;
import com.github.pieter_groenendijk.service.product.EventPoolListener;

public class LoanCycleListener extends EventPoolListener {
    public LoanCycleListener(
        EventEmitterPool eventEmitterPool,
        LoanEventScheduler scheduler,
        LoanFineService fineService
    ) {

        super(
            eventEmitterPool,
            new EventListener[]{
                new AlmostOverdueEventListener(),
                new OverdueEventListener(),
                new DayOverdueEventListener(
                    scheduler,
                    fineService
                )
            }
        );
    }
}
