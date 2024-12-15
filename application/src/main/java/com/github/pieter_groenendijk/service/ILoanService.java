package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Loan;

import java.util.Date;
import java.util.List;


public interface ILoanService {
    Loan store(Loan loan);

    Loan getLoanById(long loanId);
    Loan extendLoan(long loanId, Date dueDate);
    void cancelLoan(long loanId);


    void generateReturnByDate(long membershipId, long copyId, Date returnBy);
    void returnToCatalogue(long CopyId);

    void handleOverdueLoans();
    boolean checkIsLate(long loanId, Date currentDate, Date returnBy);
    boolean checkIsDamaged(long loanId);

   List<Loan> retrieveLoanByMembershipId(long membershipId);

    Loan retrieveLoanByLoanId(long loanId);
}
