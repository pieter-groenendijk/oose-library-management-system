package com.github.pieter_groenendijk.service.loan;

import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.model.DTO.LoanRequestDTO;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.repository.loan.ILoanRepository;
import com.github.pieter_groenendijk.model.LoanStatus;
import com.github.pieter_groenendijk.model.Membership;
import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.model.product.ProductCopy;
import com.github.pieter_groenendijk.model.product.ProductCopyStatus;
import com.github.pieter_groenendijk.service.loan.event.ILoanEventService;

import static com.github.pieter_groenendijk.service.ServiceUtils.LOAN_LENGTH;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import com.github.pieter_groenendijk.repository.IProductRepository;
import com.github.pieter_groenendijk.service.reservation.IReservationService;

import java.time.LocalDate;
import java.util.List;


public class LoanService implements ILoanService {
    private final IReservationService reservationService;
    private ILoanRepository loanRepository;
    private IProductRepository productRepository;
    private final ILoanEventService EVENT_SERVICE;
    private final IMembershipRepository membershipRepository;


    public LoanService(
        ILoanRepository loanRepository,
        IMembershipRepository membershipRepository,
        ILoanEventService eventService,
        IReservationService reservationService,
        IProductRepository productRepository
        IMembershipTypeRepository membershipTypeReposity
    ) {
        this.loanRepository = loanRepository;
        this.membershipRepository = membershipRepository;
        this.EVENT_SERVICE = eventService;
        this.reservationService = reservationService;
        this.productRepository = productRepository;
        this.membershipTypeRepository = membershipTypeRepository;
    }

    // TODO: Implement correct error handling. Is a loan still successful if we failed to schedule events for it, or the other way around?
    @Override
    public Loan store(LoanRequestDTO loanRequestDTO) {
        if (loanRequestDTO == null) {
            throw new IllegalArgumentException("LoanRequestDTO cannot be null.");
        }
        Loan loan = new Loan();


        if (loanRequestDTO.getLoanStatus() == null) {
            loan.setLoanStatus(LoanStatus.ACTIVE);
        } else {
            loan.setLoanStatus(loanRequestDTO.getLoanStatus());
        }

        loan.setStartDate(loanRequestDTO.getStartDate());
        loan.setReturnBy(LocalDate.now().plusDays(LOAN_LENGTH));

        Membership membership = membershipRepository.retrieveMembershipById(loanRequestDTO.getMembershipId())
                .orElseThrow(() -> new EntityNotFoundException("Membership not found"));
        loan.setMembership(membership);

        ProductCopy productCopy = productRepository.retrieveProductCopyById(loanRequestDTO.getProductCopyId())
                .orElseThrow(() -> new EntityNotFoundException("ProductCopy not found"));
        loan.setProductCopy(productCopy);

        productCopy.setAvailabilityStatus(ProductCopyStatus.LOANED);
        productRepository.updateProductCopy(productCopy);

        MembershipType membershipType =

        loanRepository.store(loan);
        EVENT_SERVICE.handleEventsForNewLoan(loan);

        return loan;
    }

    @Override
    public void extendLoan(long loanId, LocalDate returnBy) {
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
        returnToCatalog(loan.getProductCopy());
    }

    @Override
    public void returnToCatalog(long productCopyId) {
        ProductCopy productCopy = productRepository.retrieveProductCopyById(productCopyId)
                .orElseThrow(() -> new EntityNotFoundException("ProductCopy not found with ID: " + productCopyId));

        productCopy.setAvailabilityStatus(ProductCopyStatus.AVAILABLE);
        productRepository.updateProductCopy(productCopy);
    }

    @Override
    public void handleOverdueLoans() {
        LocalDate currentDate = LocalDate.now();

        List<Loan> activeLoans = loanRepository.retrieveAllActiveLoans();
        for (Loan loan : activeLoans) {
            try {
                if (loan.getLoanStatus().isOverdue(currentDate, loan)) {
                    loan.setLoanStatus(LoanStatus.OVERDUE);
                    loanRepository.updateLoan(loan);
                }
            } catch (EntityNotFoundException e) {
                System.out.println("No active loans found" + e.getMessage());
            }
        }
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

    @Override
    public Loan convertReservationToLoan(Reservation reservation) {
        reservationService.markReservationAsLoaned(reservation.getReservationId());

        Loan loan = new Loan();
        loan.setProductCopy(reservation.getProductCopy());
        loan.setMembership(reservation.getMembership());
        loan.setStartDate(LocalDate.now());
        loan.setReturnBy(LocalDate.now().plusDays(LOAN_LENGTH));
        loan.setLoanStatus(LoanStatus.ACTIVE);
        return loanRepository.store(loan);
    }

    public boolean doesLoanExceedLimit (Membership membership, ProductCopy productCopy) {
        List<Loan> activeLoanList = retrieveActiveLoansByMembershipId(membership.getMembershipId());
        int numberOfLoans = activeLoanList.length();

        MembershipType membershipType = membershipRepository.retrieveMembershipTypeById(membership.getMembershipTypeId());
        int maxNumberOfLoans = membershipType.getMaxLendings();

        if
    }
}
