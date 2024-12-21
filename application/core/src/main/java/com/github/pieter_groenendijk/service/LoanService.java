package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.exception.InputValidationException;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.repository.ILoanRepository;
import static com.github.pieter_groenendijk.service.ServiceUtils.LOAN_LENGTH;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class LoanService implements ILoanService {

    private ILoanRepository loanRepository;
    public LoanService(ILoanRepository loanRepository) {
            this.loanRepository = loanRepository;
        }
        @Override
    public Loan store(Loan loan) {
        if (loan == null) {
            throw new InputValidationException("Loan cannot be null");
        }
        return loanRepository.store(loan);
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
