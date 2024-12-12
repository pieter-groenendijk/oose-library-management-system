package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Loan;

import java.util.Optional;

public class LoanRepository implements ILoanRepository {

    @Override
    public Optional<Loan> retrieveLoanByLoanId(long loanId) {
        return Optional.empty();
    }

    @Override
    public Optional<Loan> retrieveLoanByMembershipId(long MembershipId) {
        return Optional.empty();
    }

    @Override
    public Loan store(Loan loan) {
        return null;
    }

    @Override
    public Loan updateLoan(Loan loan) {
        return null;
    }

    @Override
    public Loan deleteLoanByLoanId(long loanId) {
        return null;
    }
}
