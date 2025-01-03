package com.github.pieter_groenendijk.model.event;

import com.github.pieter_groenendijk.model.Loan;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("loan")
public class LoanEvent extends Event<Loan> {
    @ManyToOne
    @JoinColumn(
        name = "loan",
        nullable = false
    )
    private Loan loan;

    @Override
    public Loan getAssociation() {
        return this.loan;
    }

    @Override
    public void setAssociation(Loan loan) {
        this.loan = loan;
    }
}
