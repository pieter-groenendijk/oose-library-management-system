package com.github.pieter_groenendijk.service.loan.fine;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.fine.LoanFine;
import com.github.pieter_groenendijk.service.fine.type.FineTypeRegistry;
import com.github.pieter_groenendijk.service.loan.fine.generator.DayOverdueLoanFineGenerator;

public class LoanFineFactory {
    private final DayOverdueLoanFineGenerator DAY_OVERDUE_GENERATOR;

    // TODO: Change FineTypeRegistry to an IFineTypeRegistry
    public LoanFineFactory(FineTypeRegistry registry) {
        this.DAY_OVERDUE_GENERATOR = new DayOverdueLoanFineGenerator(registry);
    }

    public LoanFine createDayOverdueFine(Account account, Loan loan) {
        return this.DAY_OVERDUE_GENERATOR.generate(
            account,
            loan
        );
    }
}

