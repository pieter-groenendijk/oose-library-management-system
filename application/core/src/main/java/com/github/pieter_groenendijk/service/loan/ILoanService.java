package com.github.pieter_groenendijk.service.loan;

import com.github.pieter_groenendijk.model.Loan;

import java.time.LocalDate;
import java.util.List;


public interface ILoanService {
    Loan store(Loan loan);

    Loan extendLoan(long loanId, LocalDate returnBy);

    LocalDate generateReturnByDate(LocalDate returnBy);

    void returnToCatalogue(long productCopyId);
    void returnLoan(long loanId);

    void handleOverdueLoans();

    boolean checkIsLate(Loan loan);

    Loan retrieveLoanByLoanId(long loanId);

    List<Loan> retrieveActiveLoansByMembershipId(long membershipId);
}
