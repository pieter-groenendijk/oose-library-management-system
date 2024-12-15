package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Loan;


public interface ILoanService {
    Loan createLoan(Loan loan);
    Loan getLoanById(long LoanId);
    Loan extendLoan(long loanId);
    void deleteLoan(long loanId);


    void returnToCatalogue();
    void handleOverdueLoans();
    boolean checkIsLate();
    boolean checkIsDamaged();
    




}
