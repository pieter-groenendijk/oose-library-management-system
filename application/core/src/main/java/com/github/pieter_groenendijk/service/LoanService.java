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
    public Loan extendLoan(long loanId, Date returnBy) {
            Loan loan = loanRepository.retrieveLoanByLoanId(loanId)
                    .orElseThrow(() -> new IllegalArgumentException("Loan not found with id: " + loanId));

            if (loan.isExtended()) {
                throw new IllegalStateException("Loan can only be extended once.");
            }

            Date extendedReturnBy = generateReturnByDate(loan.getReturnBy());

            loan.setReturnBy(extendedReturnBy);
            loan.setExtended(true);

            return loanRepository.store(loan);
        }


    @Override
    public Date generateReturnByDate(Date returnBy) {
        LocalDate loanDate = LocalDate.now();
        LocalDate returnByDate = loanDate.plusDays(LOAN_LENGTH);
        return Date.from(returnByDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public void returnToCatalogue(long productId) {

    }

    @Override
    public void handleOverdueLoans() {

    }

    @Override
    public boolean checkIsLate(Date currentDate, Date dueDate) {
        return currentDate.after(dueDate);
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
