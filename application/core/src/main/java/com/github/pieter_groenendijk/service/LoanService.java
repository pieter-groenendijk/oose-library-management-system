package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.exception.InputValidationException;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.LoanStatus;
import com.github.pieter_groenendijk.repository.ILoanRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import static com.github.pieter_groenendijk.service.ServiceUtils.*;


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

            if (loan.getLoanStatus() == null) {
                loan.setLoanStatus(LoanStatus.ACTIVE);
            }

        return loanRepository.store(loan);
}
    @Override
    public Loan extendLoan(long loanId, Date returnBy) {
            Loan loan = loanRepository.retrieveLoanByLoanId(loanId)
                    .orElseThrow(() -> new IllegalArgumentException("Loan not found with id: " + loanId));

            if (loan.getLoanStatus().equals(LoanStatus.EXTENDED)) {
                throw new IllegalStateException("Loan can only be extended once.");
            }

            Date extendedReturnBy = generateReturnByDate(loan.getReturnBy());

            loan.setReturnBy(extendedReturnBy);
            loan.setLoanStatus(LoanStatus.EXTENDED);

            return loanRepository.updateLoan(loan);
        }


    @Override
    public Date generateReturnByDate(Date returnBy) {
        LocalDate loanDate = LocalDate.now();
        LocalDate returnByDate = loanDate.plusDays(LOAN_LENGTH);
        return Date.from(returnByDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public void returnLoan(long loanId) {
        Loan loan = loanRepository.retrieveLoanByLoanId(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found with id: " + loanId));

        if (loan.getLoanStatus() == LoanStatus.RETURNED) {
            throw new IllegalStateException("Loan has already been returned.");
        }

        loan.setLoanStatus(LoanStatus.RETURNED);
        loanRepository.updateLoan(loan);
    }


    @Override
    public void returnToCatalogue(long productId) {
    //ProductCopy productCopy = productCopyRepository.findByProductId(productId);
      //  productCopy.setStatus(AVAILABLE);
        //productCopyRepository.store(productCopy);
    }
    @Override
    public void handleOverdueLoans() {
        //if LOAN_OVERDUE.equals(loan.getLoanStatus()) {
        //  loan.setLoanStatus(LOAN_RETURNED);
        //    loanRepository.updateLoan(loan);
        // }
    }
    @Override
    public boolean checkIsLate(Loan loan) {
        Date currentDate = new Date();

        boolean isLate = loan.getLoanStatus().isOverdue(currentDate, loan);

        if (isLate && loan.getLoanStatus() != LoanStatus.OVERDUE) {
            loan.setLoanStatus(LoanStatus.OVERDUE);
            loanRepository.updateLoan(loan);
        }

        return isLate;
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
