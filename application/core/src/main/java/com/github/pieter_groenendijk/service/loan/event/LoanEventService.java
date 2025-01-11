package com.github.pieter_groenendijk.service.loan.event;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.service.loan.event.generator.AlmostOverdueLoanDetachedEventGenerator;
import com.github.pieter_groenendijk.service.loan.event.generator.DayOverdueLoanDetachedEventGenerator;
import com.github.pieter_groenendijk.service.event.scheduling.EventScheduler;
import com.github.pieter_groenendijk.service.loan.event.generator.OverdueLoanDetachedEventGenerator;

public class LoanEventService implements ILoanEventService {
    private final DayOverdueLoanDetachedEventGenerator DAY_OVERDUE_GENERATOR;
    private final OverdueLoanDetachedEventGenerator OVERDUE_GENERATOR;
    private final AlmostOverdueLoanDetachedEventGenerator ALMOST_OVERDUE_GENERATOR;

    private final EventScheduler SCHEDULER;

    public LoanEventService(
        IEventRepository repository,
        EventScheduler scheduler
    ) {
        this.DAY_OVERDUE_GENERATOR = new DayOverdueLoanDetachedEventGenerator(repository);
        this.OVERDUE_GENERATOR = new OverdueLoanDetachedEventGenerator(repository);
        this.ALMOST_OVERDUE_GENERATOR = new AlmostOverdueLoanDetachedEventGenerator(repository);

        this.SCHEDULER = scheduler;
    }

    @Override
    public void scheduleEventsForNewLoan(Loan loan) {
        // TODO: Maybe optimize so that only one event is initially scheduled
        this.scheduleAlmostOverdueLoanEvent(loan);
        this.scheduleOverdueLoanEvent(loan);
    }

    private void scheduleDayOverdueLoanEvent(Loan loan) {
        this.SCHEDULER.schedule(
            this.DAY_OVERDUE_GENERATOR.generate(loan)
        );
    }

    private void scheduleOverdueLoanEvent(Loan loan) {
        this.SCHEDULER.schedule(
            this.OVERDUE_GENERATOR.generate(loan)
        );
    }

    private void scheduleAlmostOverdueLoanEvent(Loan loan) {
        this.SCHEDULER.schedule(
            this.ALMOST_OVERDUE_GENERATOR.generate(loan)
        );
    }
}
