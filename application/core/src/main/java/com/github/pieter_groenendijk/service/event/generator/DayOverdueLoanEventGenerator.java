package com.github.pieter_groenendijk.service.event.generator;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.event.DayOverdueLoanEvent;
import com.github.pieter_groenendijk.service.event.detached.DayOverdueLoanDetachedEvent;
import com.github.pieter_groenendijk.utils.time.TimeUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DayOverdueLoanEventGenerator extends EventGenerator {
    public DayOverdueLoanEventGenerator() {

    }

    public DayOverdueLoanDetachedEvent generate(Loan loan) {
        return new DayOverdueLoanDetachedEvent(
            this.generateEvent(loan)
        );
    }

    private DayOverdueLoanEvent generateEvent(Loan loan) {
        DayOverdueLoanEvent event = new DayOverdueLoanEvent();
        event.setContext(loan);
        event.setScheduledAt(
            this.determineScheduledDateTime(loan)
        );

        return event;
    }

    private LocalDateTime determineScheduledDateTime(Loan loan) {
        // TODO: Remove transformation
        // At the start of the day after the returnBy date.
        return TimeUtils.dateToLocalDateTime(loan.getReturnBy())
            .plusDays(1)
            .with(LocalTime.MIDNIGHT);
    }
}
