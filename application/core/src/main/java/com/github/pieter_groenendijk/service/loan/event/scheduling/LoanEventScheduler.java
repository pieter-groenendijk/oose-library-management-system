package com.github.pieter_groenendijk.service.loan.event.scheduling;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.repository.loan.event.ILoanEventRepostory;
import com.github.pieter_groenendijk.service.event.scheduling.EventScheduler;
import com.github.pieter_groenendijk.service.loan.event.generator.AlmostOverdueLoanDetachedEventGenerator;
import com.github.pieter_groenendijk.service.loan.event.generator.DayOverdueLoanDetachedEventGenerator;
import com.github.pieter_groenendijk.service.loan.event.generator.OverdueLoanDetachedEventGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanEventScheduler {
    private final DayOverdueLoanDetachedEventGenerator DAY_OVERDUE_GENERATOR;
    private final OverdueLoanDetachedEventGenerator OVERDUE_GENERATOR;
    private final AlmostOverdueLoanDetachedEventGenerator ALMOST_OVERDUE_GENERATOR;

    private final IEventRepository EVENT_REPOSITORY;
    private final ILoanEventRepostory LOAN_EVENT_REPOSITORY;
    private final EventScheduler SCHEDULER;
@Autowired
    public LoanEventScheduler(
        IEventRepository repository,
        ILoanEventRepostory loanEventRepository,
        EventScheduler scheduler
    ) {
        this.DAY_OVERDUE_GENERATOR = new DayOverdueLoanDetachedEventGenerator(repository);
        this.OVERDUE_GENERATOR = new OverdueLoanDetachedEventGenerator(repository);
        this.ALMOST_OVERDUE_GENERATOR = new AlmostOverdueLoanDetachedEventGenerator(repository);

        this.EVENT_REPOSITORY = repository;
        LOAN_EVENT_REPOSITORY = loanEventRepository;
        this.SCHEDULER = scheduler;
    }

    public void scheduleDayOverdueLoanEvent(Loan loan) {
        this.SCHEDULER.schedule(
            this.DAY_OVERDUE_GENERATOR.generate(loan)
        );
    }

    public void scheduleOverdueLoanEvent(Loan loan) {
        this.SCHEDULER.schedule(
            this.OVERDUE_GENERATOR.generate(loan)
        );
    }

    public void scheduleAlmostOverdueLoanEvent(Loan loan) {
        this.SCHEDULER.schedule(
            this.ALMOST_OVERDUE_GENERATOR.generate(loan)
        );
    }

    public void cancelDuenessLoanEvents(Loan loan) throws Exception {
        this.LOAN_EVENT_REPOSITORY.cancelDuenessEventsForLoan(loan);
    }
}
