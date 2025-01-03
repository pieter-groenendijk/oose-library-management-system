package com.github.pieter_groenendijk.service.loan;

import com.github.pieter_groenendijk.model.Loan;

import java.util.Date;
import java.util.List;


public interface ILoanService {
    Loan store(Loan loan);

    Loan extendLoan(long loanId, Date dueDate);

    Date generateReturnByDate(long copyId, Date returnBy);

    void returnToCatalogue(long CopyId);

    void handleOverdueLoans();
    boolean checkIsLate(Date currentDate, Date returnBy);
    boolean checkIsDamaged(long loanId);

    Loan retrieveLoanByLoanId(long loanId);

    List<Loan> retrieveActiveLoansByMembershipId(long membershipId);
}
