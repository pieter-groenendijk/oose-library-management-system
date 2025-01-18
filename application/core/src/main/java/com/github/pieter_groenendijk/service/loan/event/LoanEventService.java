package com.github.pieter_groenendijk.service.loan.event;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.service.loan.event.generator.AlmostOverdueLoanDetachedEventGenerator;
import com.github.pieter_groenendijk.service.loan.event.generator.DayOverdueLoanDetachedEventGenerator;
import com.github.pieter_groenendijk.service.event.scheduling.EventScheduler;
import com.github.pieter_groenendijk.service.loan.event.generator.OverdueLoanDetachedEventGenerator;
import com.github.pieter_groenendijk.service.loan.event.scheduling.LoanEventScheduler;

public class LoanEventService implements ILoanEventService {
    private final LoanEventScheduler SCHEDULER;


    public LoanEventService(
        LoanEventScheduler scheduler
    ) {
        this.SCHEDULER = scheduler;
    }

    public void handleEventsForReturnedLoan(Loan loan) {

    }

    @Override
    public void handleEventsForNewLoan(Loan loan) {
        // TODO: Maybe optimize so that only one event is initially scheduled
        this.SCHEDULER.scheduleAlmostOverdueLoanEvent(loan);
        this.SCHEDULER.scheduleOverdueLoanEvent(loan);
    }
}
