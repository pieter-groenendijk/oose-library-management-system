package com.github.pieter_groenendijk.repository.loan;

import com.github.pieter_groenendijk.model.Loan;

import java.util.List;
import java.util.Optional;

public interface ILoanRepository {

    Optional<Loan> retrieveLoanByLoanId(long loanId);
    List<Loan> retrieveActiveLoansByMembershipId(long MembershipId);
    Loan store(Loan loan);
    Loan updateLoan(Loan loan);

}
