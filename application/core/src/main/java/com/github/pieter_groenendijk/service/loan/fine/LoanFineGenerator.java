package com.github.pieter_groenendijk.service.loan.fine;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.fine.FineType;
import com.github.pieter_groenendijk.model.fine.LoanFine;
import com.github.pieter_groenendijk.service.fine.generator.FineGenerator;

public class LoanFineGenerator extends FineGenerator<Loan, LoanFine> {
    protected LoanFineGenerator(FineType type) {
        super(type);
    }

    @Override
    protected LoanFine generateEmpty() {
        return new LoanFine();
    }

    @Override
    protected void setFineAssociation(LoanFine fine, Loan loan) {
        fine.setLoan(loan);
    }
}
