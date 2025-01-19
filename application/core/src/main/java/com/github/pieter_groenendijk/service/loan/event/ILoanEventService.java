package com.github.pieter_groenendijk.service.loan.event;

import com.github.pieter_groenendijk.model.Loan;

public interface ILoanEventService {
    void handleEventsForNewLoan(Loan loan);
}
