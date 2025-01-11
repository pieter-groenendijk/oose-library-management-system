package com.github.pieter_groenendijk.service.loan.event.generator;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.repository.event.IEventRepository;

import java.time.LocalDateTime;

public class AlmostOverdueLoanDetachedEventGenerator extends DetachedLoanEventGenerator {
    protected AlmostOverdueLoanDetachedEventGenerator(IEventRepository repository) {
        super(
            repository,
            EventType.ALMOST_OVERDUE_LOAN
        );
    }

    @Override
    protected LocalDateTime determineScheduledDateTime(Loan loan) {
        return loan
            .getReturnBy()
            .atStartOfDay(); // On the beginning of the last day, without getting a fine
    }
}
