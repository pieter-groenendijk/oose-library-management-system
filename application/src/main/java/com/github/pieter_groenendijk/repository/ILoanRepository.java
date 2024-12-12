package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Loan;

import java.util.Optional;

public interface ILoanRepository {

    Optional<Loan> retrieveLoanByLoanId(long loanId);
    Loan store(Loan loan);
    Loan updateLoan(Loan loan);
    Loan deleteLoanByLoanId(long loanId);

}
