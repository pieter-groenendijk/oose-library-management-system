package com.github.pieter_groenendijk.service.loan.event.generator;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.model.event.LoanEvent;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.service.event.generator.DetachedEventGenerator;
import com.github.pieter_groenendijk.utils.scheduling.TaskStorage;

import java.time.LocalDateTime;

abstract class DetachedLoanEventGenerator extends DetachedEventGenerator<Loan, LoanEvent> {
    protected DetachedLoanEventGenerator(IEventRepository repository, EventType type) {
        super(repository, type);
    }

    @Override
    protected LoanEvent generateEmptyEvent() {
        return new LoanEvent();
    }

    @Override
    protected TaskStorage<LoanEvent> generateEventStorage() {
        return this.REPOSITORY::storeLoanEvent;
    }

    @Override
    protected abstract LocalDateTime determineScheduledDateTime(Loan loan);
}
