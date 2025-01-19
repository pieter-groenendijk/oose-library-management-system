package com.github.pieter_groenendijk.service.loan.event;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.service.loan.event.scheduling.LoanEventScheduler;

public class LoanEventService implements ILoanEventService {
    private final LoanEventScheduler SCHEDULER;


    public LoanEventService(
        LoanEventScheduler scheduler
    ) {
        this.SCHEDULER = scheduler;
    }

    // TODO: Make public when done
    private void handleEventsForReturnedLoan(Loan loan) {
    }

    @Override
    public void handleEventsForNewLoan(Loan loan) throws Exception {
        // TODO: Maybe optimize so that only one event is initially scheduled
        this.SCHEDULER.scheduleAlmostOverdueLoanEvent(loan);
        this.SCHEDULER.scheduleOverdueLoanEvent(loan);
    }
}
