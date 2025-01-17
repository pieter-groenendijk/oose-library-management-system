package com.github.pieter_groenendijk.service.loan;

import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.model.DTO.LoanRequestDTO;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.LoanStatus;
import com.github.pieter_groenendijk.model.Membership;
import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.model.product.ProductCopy;
import com.github.pieter_groenendijk.model.product.ProductCopyStatus;
import com.github.pieter_groenendijk.repository.ILoanRepository;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import com.github.pieter_groenendijk.repository.IProductRepository;
import com.github.pieter_groenendijk.service.IReservationService;
import com.github.pieter_groenendijk.service.loan.event.ILoanEventService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.github.pieter_groenendijk.service.ServiceUtils.LOAN_LENGTH;


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
    ) {
        this.loanRepository = loanRepository;
        this.membershipRepository = membershipRepository;
        this.EVENT_SERVICE = eventService;
        this.reservationService = reservationService;
        this.productRepository = productRepository;

    }

    @Override
    public Loan store(LoanRequestDTO loanRequestDTO) {
        validateLoanRequestDTO(loanRequestDTO);

        Loan loan = new Loan();
        setLoanStatus(loan, loanRequestDTO);
        setLoanDates(loan);


        Membership membership = membershipRepository.retrieveMembershipById(loanRequestDTO.getMembershipId())
                .orElseThrow(() -> new EntityNotFoundException("Membership not found"));
        loan.setMembership(membership);

        ProductCopy productCopy = productRepository.retrieveProductCopyById(loanRequestDTO.getProductCopyId())
                .orElseThrow(() -> new EntityNotFoundException("ProductCopy not found"));
        loan.setProductCopy(productCopy);

        productCopy.setAvailabilityStatus(ProductCopyStatus.LOANED);
        productRepository.updateProductCopy(productCopy);

        loanRepository.store(loan);
        EVENT_SERVICE.scheduleEventsForNewLoan(loan);

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
        return getCurrentDate().plusDays(LOAN_LENGTH);
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
        LocalDate currentDate = getCurrentDate();

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
        LocalDate currentDate = getCurrentDate();
        boolean isLate = loan.getLoanStatus().isOverdue(currentDate, loan);

        if (isLate) {
            updateLoanStatusIfNeeded(loan);
        }

        return isLate;
    }

    @Override
    public void updateLoanStatusIfNeeded(Loan loan) {
        if (loan.getLoanStatus() != LoanStatus.OVERDUE) {
            loan.setLoanStatus(LoanStatus.OVERDUE);
            loanRepository.updateLoan(loan);
        }
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
        loan.setStartDate(getCurrentDate());
        loan.setReturnBy(getCurrentDate().plusDays(LOAN_LENGTH));
        loan.setLoanStatus(LoanStatus.ACTIVE);
        return loanRepository.store(loan);
    }

    private LocalDate getCurrentDate() {
        return LocalDate.now();
    }
    private void setLoanStatus(Loan loan, LoanRequestDTO loanRequestDTO) {
        LoanStatus loanStatus = Optional.ofNullable(loanRequestDTO.getLoanStatus())
                .orElse(LoanStatus.ACTIVE);
        loan.setLoanStatus(loanStatus);
    }

    private void setLoanDates(Loan loan) {
        loan.setStartDate(LocalDate.now());
        loan.setReturnBy(getCurrentDate().plusDays(LOAN_LENGTH));
    }
    private void validateLoanRequestDTO(LoanRequestDTO loanRequestDTO) {
        if (loanRequestDTO == null) {
            throw new IllegalArgumentException("LoanRequestDTO cannot be null.");
        }
    }

}
