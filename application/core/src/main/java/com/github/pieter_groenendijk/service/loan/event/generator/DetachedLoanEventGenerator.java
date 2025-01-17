package com.github.pieter_groenendijk.service.loan.event.generator;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.model.event.LoanEvent;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.scheduling.TaskStorage;
import com.github.pieter_groenendijk.service.event.generator.DetachedEventGenerator;

import java.time.LocalDateTime;

abstract class DetachedLoanEventGenerator extends DetachedEventGenerator<Loan, LoanEvent> {
    protected DetachedLoanEventGenerator(IEventRepository repository, EventType type) {
        super(repository, type);
    }

    @Override
    protected abstract LocalDateTime determineScheduledDateTime(Loan loan);

    @Override
    protected LoanEvent generateEmptyEvent() {
        return new LoanEvent();
    }

    @Override
    protected TaskStorage<LoanEvent> generateEventStorage() {
        // TODO: Hibernate determines the object type at runtime, so the need for generating event storage should be reconsidered.
        return super.REPOSITORY::store;
    }
}
