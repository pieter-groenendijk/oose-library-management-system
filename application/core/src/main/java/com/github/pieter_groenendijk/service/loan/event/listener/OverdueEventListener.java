package com.github.pieter_groenendijk.service.loan.event.listener;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.service.event.listener.EventListener;
import com.github.pieter_groenendijk.service.loan.event.scheduling.LoanEventScheduler;
import com.github.pieter_groenendijk.service.loan.fine.LoanFineService;

class OverdueEventListener extends EventListener<Loan> {
    private final LoanEventScheduler SCHEDULER;

    public OverdueEventListener(LoanEventScheduler scheduler) {
        super(
            EventType.OVERDUE_LOAN
        );

        this.SCHEDULER = scheduler;
    }

    @Override
    public void react(Loan loan) {
        this.SCHEDULER.scheduleDayOverdueLoanEvent(loan);

        // TODO: Schedule notification
    }
}