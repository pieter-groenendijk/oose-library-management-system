package com.github.pieter_groenendijk.service.loan;

import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.exception.InputValidationException;
import com.github.pieter_groenendijk.model.DTO.LoanRequestDTO;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.LoanStatus;
import com.github.pieter_groenendijk.model.Membership;
import com.github.pieter_groenendijk.model.product.ProductCopy;
import com.github.pieter_groenendijk.model.product.ProductCopyStatus;
import com.github.pieter_groenendijk.repository.ILoanRepository;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import com.github.pieter_groenendijk.repository.IProductRepository;
import com.github.pieter_groenendijk.service.loan.event.ILoanEventService;

import java.time.LocalDate;
import java.util.List;

import static com.github.pieter_groenendijk.service.ServiceUtils.LOAN_LENGTH;


public class LoanService implements ILoanService {
    private ILoanRepository loanRepository;
    private IProductRepository productRepository;
    private final ILoanEventService EVENT_SERVICE;
    private final IMembershipRepository membershipRepository;


    public LoanService(
        ILoanRepository loanRepository,
        IMembershipRepository membershipRepository,
        ILoanEventService eventService
    ) {
        this.loanRepository = loanRepository;
        this.membershipRepository = membershipRepository;
        this.EVENT_SERVICE = eventService;
    }

    // TODO: Implement correct error handling. Is a loan still successful if we failed to schedule events for it, or the other way around?
    @Override
    public Loan store(LoanRequestDTO loanRequestDTO) {
        if (loanRequestDTO == null) {
            throw new InputValidationException("LoanRequestDTO cannot be null");
        }

        Loan loan = new Loan();
        if (loanRequestDTO.getLoanStatus() == null) {
            loan.setLoanStatus(LoanStatus.ACTIVE);
        } else {
            loan.setLoanStatus(loanRequestDTO.getLoanStatus());
        }

        loan.setStartDate(loanRequestDTO.getStartDate());
        loan.setReturnBy(loanRequestDTO.getReturnBy());

        long membershipId = loanRequestDTO.getMembershipId();

        Membership membership = membershipRepository.retrieveMembershipById(membershipId)
                .orElseThrow(() -> new EntityNotFoundException("Membership not found with ID: " + membershipId));
        loan.setMembership(membership);

        Long productCopyId = loanRequestDTO.getProductCopyId();
        if (productCopyId == null) {
            throw new EntityNotFoundException("ProductCopy ID not found in the loan request");
        }

        ProductCopy productCopy = productRepository.retrieveProductCopyById(productCopyId)
                .orElseThrow(() -> new EntityNotFoundException("ProductCopy not found with ID: " + productCopyId));

        productCopy.setAvailabilityStatus(ProductCopyStatus.LOANED);  // Set the status of the product copy to LOANED
        productRepository.updateProductCopy(productCopy);
        loan.setProductCopyId(productCopy);
        loanRepository.store(loan);

        EVENT_SERVICE.scheduleEventsForNewLoan(loan);

        return loan;
    }


    @Override
    public Loan extendLoan(long loanId, LocalDate returnBy) {
        Loan loan = loanRepository.retrieveLoanByLoanId(loanId);
        if (loan == null) {
            throw new IllegalArgumentException("Loan not found with id: " + loanId);
        }

        if (loan.getLoanStatus().equals(LoanStatus.EXTENDED)) {
            throw new IllegalStateException("Loan can only be extended once.");
        }

        LocalDate extendedReturnBy = generateReturnByDate(loan.getReturnBy());

        loan.setReturnBy(extendedReturnBy);
        loan.setLoanStatus(LoanStatus.EXTENDED);

        return loanRepository.updateLoan(loan);
    }

    @Override
    public LocalDate generateReturnByDate(LocalDate returnBy) {
        return LocalDate.now().plusDays(LOAN_LENGTH);
    }


    @Override
    public void returnLoan(long loanId) {
        Loan loan = loanRepository.retrieveLoanByLoanId(loanId);
        if (loan == null) {
            throw new EntityNotFoundException("Loan not found with id: " + loanId);
        }

        if (loan.getLoanStatus() == LoanStatus.RETURNED) {
            throw new IllegalStateException("Loan has already been returned.");
        }

        loan.setLoanStatus(LoanStatus.RETURNED);
        loanRepository.updateLoan(loan);
        returnToCatalogue(loan.getProductCopy());
    }


    @Override
    public void returnToCatalogue(long productCopyId) {
        ProductCopy productCopy = productRepository.retrieveProductCopyById(productCopyId)
                .orElseThrow(() -> new EntityNotFoundException("ProductCopy not found with ID: " + productCopyId));

        productCopy.setAvailabilityStatus(ProductCopyStatus.AVAILABLE);
        productRepository.updateProductCopy(productCopy);
    }

    @Override
    public void handleOverdueLoans() {

    }

    @Override
    public boolean checkIsLate(Loan loan) {
        LocalDate currentDate = LocalDate.now();

        boolean isLate = loan.getLoanStatus().isOverdue(currentDate, loan);

        if (isLate && loan.getLoanStatus() != LoanStatus.OVERDUE) {
            loan.setLoanStatus(LoanStatus.OVERDUE);
            loanRepository.updateLoan(loan);
        }

        return isLate;
    }

    @Override
    public Loan retrieveLoanByLoanId(long loanId) {
        Loan loan = loanRepository.retrieveLoanByLoanId(loanId);
        if (loan == null) {
            throw new EntityNotFoundException("No loan found for Loan ID: " + loanId);
        }
        return loan;
    }

    @Override
    public List<Loan> retrieveActiveLoansByMembershipId(long membershipId) {
        List<Loan> loans = loanRepository.retrieveActiveLoansByMembershipId(membershipId);
        if (loans.isEmpty()) {
            throw new EntityNotFoundException("Membership with ID" + membershipId + " not found.");
    }
        return loans;
    }


}
