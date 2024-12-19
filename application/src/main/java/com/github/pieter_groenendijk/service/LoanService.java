package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.repository.ILoanRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.pieter_groenendijk.service.LibraryConstants.LOAN_LENGTH;


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
        LocalDate loanDate = LocalDate.now();
        LocalDate dueDate = loanDate.plusDays(LOAN_LENGTH);
        returnBy.setTime(Date.from(dueDate.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
    }

    @Override
    public void returnToCatalogue(long CopyId) {

    }

    @Override
    public void handleOverdueLoans() {

    }

    @Override
    public boolean checkIsLate(long loanId, Date currentDate, Date dueDate) {
        return currentDate.after(dueDate);
    }

    @Override
    public boolean checkIsDamaged(long loanId) {
        return false;
    }


    @Override
    public Loan retrieveLoanByLoanId(long loanId) {
        return loanRepository.retrieveLoanByLoanId(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Loan with ID " + loanId + " not found."));
    }

    @Override
    public List<Loan> retrieveActiveLoansByMembershipId(long membershipId) {
        try {
            List<Loan> loans = loanRepository.retrieveActiveLoansByMembershipId(membershipId);
            return loanRepository.retrieveActiveLoansByMembershipId(membershipId);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
