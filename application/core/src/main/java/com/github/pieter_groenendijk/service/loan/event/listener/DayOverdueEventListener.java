package com.github.pieter_groenendijk.service.loan.event.listener;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.service.event.listener.EventListener;
import com.github.pieter_groenendijk.service.fine.FineProcessor;
import com.github.pieter_groenendijk.service.loan.event.scheduling.LoanEventScheduler;
import com.github.pieter_groenendijk.service.loan.fine.LoanFineFactory;
import com.github.pieter_groenendijk.service.loan.fine.LoanFineService;

class DayOverdueEventListener extends EventListener<Loan> {
    private final LoanEventScheduler SCHEDULER;
    private final LoanFineService SERVICE;

    public DayOverdueEventListener(
        LoanEventScheduler scheduler,
        LoanFineService service
    ) {
        super(
            EventType.DAY_OVERDUE_LOAN
        );
        SCHEDULER = scheduler;

        this.SERVICE = service;
    }

    @Override
    public void react(Loan loan) {
        try {
            this.SCHEDULER.scheduleDayOverdueLoanEvent(loan);
            this.SERVICE.declareDayOverdueFine(loan);
        } catch (Exception e) {
            // TODO: Log
        }
    }
}