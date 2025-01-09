package com.github.pieter_groenendijk.service.loan.event.listener;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.service.event.listener.EventListener;

class AlmostOverdueEventListener extends EventListener<Loan> {
    public AlmostOverdueEventListener() {
        super(
            EventType.ALMOST_OVERDUE_LOAN
        );
    }

    @Override
    public void react(Loan loan) {

    }
}
