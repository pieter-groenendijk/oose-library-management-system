package com.github.pieter_groenendijk.repository.loan;

import com.github.pieter_groenendijk.model.Loan;

import java.util.List;

public interface ILoanRepository {

    Loan retrieveLoanByLoanId(long loanId);
    List<Loan> retrieveActiveLoansByMembershipId(long MembershipId);
    Loan store(Loan loan);
    void updateLoan(Loan loan);
    List<Loan> retrieveAllActiveLoans();
}
