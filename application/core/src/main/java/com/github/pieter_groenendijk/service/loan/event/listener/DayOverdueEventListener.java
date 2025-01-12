package com.github.pieter_groenendijk.service.loan.event.listener;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.service.event.listener.EventListener;

class DayOverdueEventListener extends EventListener<Loan> {
    public DayOverdueEventListener() {
        super(
            EventType.DAY_OVERDUE_LOAN
        );
    }

    @Override
    public void react(Loan loan) {
        // TODO: Schedule New DayOverdueEventListener

        // TODO: Declare fine
    }
}