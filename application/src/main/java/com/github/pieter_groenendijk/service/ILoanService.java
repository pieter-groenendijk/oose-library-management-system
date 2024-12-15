package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Loan;

import java.util.Date;


public interface ILoanService {
    Loan createLoan(long membershipId, long copyId, Date newDueDate);
    Loan getLoanById(long loanId);
    Loan extendLoan(long loanId, Date dueDate);
    void deleteLoan(long loanId);


    void generateDueDate(long membershipId, long copyId, Date dueDate);
    void returnToCatalogue(long CopyId);

    void handleOverdueLoans();
    boolean checkIsLate(long loanId, Date currentDate);
    boolean checkIsDamaged(long loanId);

}
