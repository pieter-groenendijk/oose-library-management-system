package com.github.pieter_groenendijk.model.fine;

import com.github.pieter_groenendijk.model.Loan;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "loan")
public class LoanFine extends Fine {
    @ManyToOne
    @JoinColumn(
        name = "loan",
        nullable = false
    )
    private Loan loan;

    public LoanFine() {}

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
