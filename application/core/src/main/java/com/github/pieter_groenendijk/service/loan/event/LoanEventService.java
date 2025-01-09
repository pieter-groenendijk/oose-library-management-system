package com.github.pieter_groenendijk.service.loan.event;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.service.loan.event.generator.DayOverdueLoanDetachedEventGenerator;
import com.github.pieter_groenendijk.service.event.scheduling.EventScheduler;

public class LoanEventService implements ILoanEventService {
    private final DayOverdueLoanDetachedEventGenerator DAY_OVERDUE_GENERATOR;

    private final EventScheduler SCHEDULER;

    public LoanEventService(
        IEventRepository repository,
        EventScheduler scheduler
    ) {
        this.DAY_OVERDUE_GENERATOR = new DayOverdueLoanDetachedEventGenerator(repository);
        this.SCHEDULER = scheduler;
    }

    @Override
    public void scheduleEventsForNewLoan(Loan loan) {
        this.scheduleDayOverdueLoanEvent(loan);
    }

    private void scheduleDayOverdueLoanEvent(Loan loan) {
        this.SCHEDULER.schedule(
            this.DAY_OVERDUE_GENERATOR.generate(loan)
        );
    }
}
