package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Loan;

import java.util.Optional;

public interface ILoanRepository {

    Optional<Loan> retrieveLoanByLoanId(long loanId);
    Optional<Loan> retrieveLoanByMembershipId(long MembershipId);
    Loan store(Loan loan);
    String updateLoan(Loan loan);
    void deleteLoanByLoanId(long loanId);

}
