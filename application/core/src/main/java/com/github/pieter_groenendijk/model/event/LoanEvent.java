package com.github.pieter_groenendijk.model.event;

import com.github.pieter_groenendijk.model.Loan;

public class LoanEvent extends Event<Loan> {

    private Loan loan;

    @Override
    public Loan getAssociation() {
        return null;
    }

    @Override
    public void setAssociation(Loan loan) {

    }
}
