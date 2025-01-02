package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.exception.InputValidationException;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.repository.ILoanRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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
                loan.setLoanStatus(LOAN_ACTIVE);
            }

        return loanRepository.store(loan);
}
    @Override
    public Loan extendLoan(long loanId, Date returnBy) {
            Loan loan = loanRepository.retrieveLoanByLoanId(loanId)
                    .orElseThrow(() -> new IllegalArgumentException("Loan not found with id: " + loanId));

            if (loan.getLoanStatus().equals(LOAN_EXTENDED)) {
                throw new IllegalStateException("Loan can only be extended once.");
            }

            Date extendedReturnBy = generateReturnByDate(loan.getReturnBy());

            loan.setReturnBy(extendedReturnBy);
            loan.setLoanStatus(LOAN_EXTENDED);

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

        if (Objects.equals(loan.getLoanStatus(), "RETURNED")) {
            throw new IllegalStateException("Loan has already been returned.");
        }

        loan.setLoanStatus(LOAN_RETURNED);

        loanRepository.store(loan);
    }

@Override
    public void returnToCatalogue(long productId) {
    //ProductCopy productCopy = productCopyRepository.findByProductId(productId);
      //  productCopy.setStatus(AVAILABLE);
        //productCopyRepository.store(productCopy);
    }
    @Override
    public void handleOverdueLoans() {

    }

    @Override
    public boolean checkIsLate(Loan loan) {
        Date currentDate = new Date();

        boolean isLate;

        if (loan.getLoanStatus().equals(LOAN_ACTIVE)) {
            isLate = currentDate.after(loan.getReturnBy());
        } else if (loan.getLoanStatus().equals(LOAN_EXTENDED)) {
            isLate = currentDate.after(loan.getExtendedReturnBy());
        } else {
            throw new IllegalStateException("Invalid loan status: " + loan.getLoanStatus());
        }

        if (isLate) {
            loan.setLoanStatus(LOAN_OVERDUE);
            loanRepository.updateLoan(loan);
        }

        return isLate;
    }
    @Override
    public boolean checkIsDamaged(long productId) {
        //ProductCopy productCopy = productCopyRepository.findByProductId(productId);

       // if (productCopy.isDamaged()) {
        //    System.out.println("Product is damaged. Will not be returned to catalogue.");
           // return true;
       // }
        //returnToCatalogue(productId);
       // productCopy.setStatus(AVAILABLE);
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
