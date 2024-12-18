package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.repository.ILoanRepository;
import java.util.Date;
import java.util.List;


public class LoanService implements ILoanService {

    private ILoanRepository loanRepository;


    public LoanService(ILoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan store(Loan loan) {
        return null;
        //return loanRepository.store(new Loan());
    }

    @Override
    public Loan store(long membershipId, long copyId) {
return null;
    }


    @Override
    public Loan getLoanById(long loanId) {
        return null;
    }

    @Override
    public Loan extendLoan(long loanId, Date dueDate) {
        return null;
    }

    @Override
    public void cancelLoan(long loanId) {

    }

    @Override
    public void generateReturnByDate(long membershipId, long copyId, Date returnBy) {

    }

    @Override
    public void returnToCatalogue(long CopyId) {

    }

    @Override
    public void handleOverdueLoans() {

    }

    @Override
    public boolean checkIsLate(long loanId, Date currentDate, Date dueDate) {
        return false;
    }

    @Override
    public boolean checkIsDamaged(long loanId) {
        return false;
    }

    @Override
    public List<Loan> retrieveLoanByMembershipId(long membershipId) {
        return null;
    }

    @Override
    public Loan retrieveLoanByLoanId(long loanId) {
        return null;
    }

    @Override
    public List<Loan> retrieveActiveLoansByMembershipId(long membershipId) {
        return null;
    }


}
