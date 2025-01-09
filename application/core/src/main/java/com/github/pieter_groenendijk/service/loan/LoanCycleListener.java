package com.github.pieter_groenendijk.service.loan;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.service.event.emitting.EventEmitterPool;
import com.github.pieter_groenendijk.service.product.ProductCycleListener;

public class LoanCycleListener extends ProductCycleListener {
    public LoanCycleListener(EventEmitterPool eventEmitterPool) {
        super(eventEmitterPool);
    }

    @Override
    public void startListening() {
        this.attachAlmostOverdueEventListener();
    }

    // TODO: Maybe split up into a class for each event type instead
    private void attachDayOverdueEventListener() {
        this.EVENT_EMITTER_POOL.attach(
            EventType.DAY_OVERDUE_LOAN,
            this::reactOnDayOverdueEvent
        );
    }

    private void attachOverdueEventListener() {
        this.EVENT_EMITTER_POOL.attach(
            EventType.DAY_OVERDUE_LOAN,
            this::reactOnOverdueEvent
        );
    }

    private void attachAlmostOverdueEventListener() {
        this.EVENT_EMITTER_POOL.attach(
            EventType.ALMOST_OVERDUE_LOAN,
            this::reactOnAlmostOverdueEvent
        );
    }

    private void reactOnAlmostOverdueEvent(Loan loan) {

    }

    private void reactOnOverdueEvent(Loan loan) {

    }

    private void reactOnDayOverdueEvent(Loan loan) {

    }
}
