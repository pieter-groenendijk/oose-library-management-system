package com.github.pieter_groenendijk.service.loan.event.listener;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.service.event.listener.EventListener;

class OverdueEventListener extends EventListener<Loan> {
    public OverdueEventListener() {
        super(
            EventType.OVERDUE_LOAN
        );
    }

    @Override
    public void react(Loan loan) {
        // TODO: Schedule DayOverdue Event

        // TODO: Schedule notification
    }
}