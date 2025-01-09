package com.github.pieter_groenendijk.service.loan.event.generator;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.event.EventType;
import com.github.pieter_groenendijk.model.event.LoanEvent;
import com.github.pieter_groenendijk.repository.event.IEventRepository;
import com.github.pieter_groenendijk.service.event.generator.DetachedEventGenerator;
import com.github.pieter_groenendijk.utils.scheduling.TaskStorage;

import java.time.LocalDateTime;

public class DayOverdueLoanDetachedEventGenerator extends DetachedLoanEventGenerator {
    public DayOverdueLoanDetachedEventGenerator(IEventRepository repository) {
        super(
            repository,
            EventType.DAY_OVERDUE_LOAN
        );
    }

    @Override
    protected LocalDateTime determineScheduledDateTime(Loan loan) {
        // At the start of the day after the returnBy date.
        return loan.getReturnBy()
            .plusDays(1)
            .atStartOfDay();
    }
}
