package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Loan;

import java.util.Date;
import java.util.List;


public interface ILoanService {
    Loan store(Loan loan);

    Loan extendLoan(long loanId, Date dueDate);

    Date generateReturnByDate(Date returnBy);

    void returnToCatalogue(long CopyId);
    void returnLoan(long loanId);

    void handleOverdueLoans();

    boolean checkIsLate(Loan loan);

    Loan retrieveLoanByLoanId(long loanId);

    List<Loan> retrieveActiveLoansByMembershipId(long membershipId);
}
