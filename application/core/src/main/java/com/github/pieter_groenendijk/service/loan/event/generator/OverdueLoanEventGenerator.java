package com.github.pieter_groenendijk.service.loan.event.generator;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.repository.event.IEventRepository;

import java.time.LocalDateTime;

public class OverdueLoanEventGenerator extends DetachedLoanEventGenerator {
    protected OverdueLoanEventGenerator(IEventRepository repository, EventType type) {
        super(repository, type);
    }

    @Override
    protected LocalDateTime determineScheduledDateTime(Loan loan) {
        return null;
    }
}
