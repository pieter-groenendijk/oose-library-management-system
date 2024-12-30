package com.github.pieter_groenendijk.service.event.generator;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.model.event.LoanEvent;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.service.event.detached.EventStorage;
import com.github.pieter_groenendijk.utils.time.TimeUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DayOverdueLoanEventGenerator extends EventGenerator<Loan, LoanEvent> {
    protected DayOverdueLoanEventGenerator(IEventRepository repository) {
        super(
            repository,
            EventType.DAY_OVERDUE_LOAN_EVENT
        );
    }

    @Override
    protected LoanEvent generateEmptyEvent() {
        return new LoanEvent();
    }

    @Override
    protected LocalDateTime determineScheduledDateTime(Loan loan) {
        // TODO: Remove transformation
        // At the start of the day after the returnBy date.
        return TimeUtils.dateToLocalDateTime(loan.getReturnBy())
            .plusDays(1)
            .with(LocalTime.MIDNIGHT);
    }

    @Override
    protected EventStorage<LoanEvent> generateEventStorage() {
        return this.REPOSITORY::storeLoanEvent;
    }
}
