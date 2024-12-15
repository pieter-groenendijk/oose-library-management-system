package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Loan;

import java.util.Date;
import java.util.List;


public interface ILoanService {
    Loan storeLoan(long membershipId, long copyId, Date newDueDate);
    Loan getLoanById(long loanId);
    Loan extendLoan(long loanId, Date dueDate);
    void cancelLoan(long loanId);


    void generateDueDate(long membershipId, long copyId, Date dueDate);
    void returnToCatalogue(long CopyId);

    void handleOverdueLoans();
    boolean checkIsLate(long loanId, Date currentDate, Date dueDate);
    boolean checkIsDamaged(long loanId);

   List<Loan> retrieveLoanByMembershipId(long membershipId);

    Loan retrieveLoanByLoanId(long loanId);
}
