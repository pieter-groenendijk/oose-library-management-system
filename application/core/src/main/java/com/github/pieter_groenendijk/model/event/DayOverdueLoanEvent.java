package com.github.pieter_groenendijk.model.event;

import com.github.pieter_groenendijk.model.Loan;

public class DayOverdueLoanEvent extends Event<Loan> {
    public DayOverdueLoanEvent() {
        this.type = EventType.DAY_OVERDUE_LOAN_EVENT;
    }
}
