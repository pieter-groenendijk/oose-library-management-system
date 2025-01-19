package com.github.pieter_groenendijk.service.loan;

import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.LoanStatus;
import com.github.pieter_groenendijk.model.Reservation;
import com.github.pieter_groenendijk.repository.loan.ILoanRepository;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import com.github.pieter_groenendijk.repository.IProductRepository;
import com.github.pieter_groenendijk.service.reservation.IReservationService;
import com.github.pieter_groenendijk.service.loan.event.ILoanEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LoanServiceTest {
    private static final int LOAN_LENGTH = 7;
    private LoanService loanService;
    private ILoanRepository mockLoanRepository;

    private ILoanEventService mockEventService;
    private IMembershipRepository mockMembershipRepository;
    private IReservationService mockReservationService;
    private IProductRepository mockProductRepository;
    private Loan mockLoan;

    @BeforeEach
    public void setUp() {
        mockLoanRepository = mock(ILoanRepository.class);
        mockEventService = mock(ILoanEventService.class);
        mockReservationService = mock(IReservationService.class);
        mockProductRepository = mock(IProductRepository.class);
        mockMembershipRepository = mock(IMembershipRepository.class);
        loanService = new LoanService(mockLoanRepository, mockMembershipRepository,mockEventService, mockReservationService, mockProductRepository);
        mockLoan = mock(Loan.class);
    }

    @Test
    void generateReturnByDate() {
        LocalDate loanDate = LocalDate.now();
        LocalDate expectedReturnDate = loanDate.plusDays(LOAN_LENGTH);
        LocalDate actualReturnDate = loanService.generateReturnByDate(loanDate);
        assertEquals(expectedReturnDate, actualReturnDate);
    }

    @Test
    void checkIsLate() {
        LocalDate dueDate = LocalDate.now().minusDays(5);
        when(mockLoan.getLoanStatus()).thenReturn(LoanStatus.ACTIVE);
        when(mockLoan.getReturnBy()).thenReturn(dueDate);
        boolean result = loanService.checkIsLate(mockLoan);
        assertTrue(result, "Loan should be marked as late");
        verify(mockLoan).setLoanStatus(LoanStatus.OVERDUE);
        verify(mockLoanRepository).updateLoan(mockLoan);
    }

    @Test
    void updateLoanStatusIfNeeded_ShouldUpdateStatus_WhenNotOverdue() {
        // Arrange
        when(mockLoan.getLoanStatus()).thenReturn(LoanStatus.ACTIVE);

        // Act
        loanService.updateLoanStatusIfNeeded(mockLoan);

        // Assert
        verify(mockLoan).setLoanStatus(LoanStatus.OVERDUE);
        verify(mockLoanRepository).updateLoan(mockLoan);
    }

    @Test
    void updateLoanStatusIfNeeded_ShouldNotUpdateStatus_WhenAlreadyOverdue() {
        // Arrange
        when(mockLoan.getLoanStatus()).thenReturn(LoanStatus.OVERDUE);

        // Act
        loanService.updateLoanStatusIfNeeded(mockLoan);

        // Assert
        verify(mockLoan, never()).setLoanStatus(any());
        verify(mockLoanRepository, never()).updateLoan(any());
    }

    @Test
    void retrieveLoanByLoanId() {
        long loanId = 123L;
        Loan mockLoan = new Loan();
        when(mockLoanRepository.retrieveLoanByLoanId(loanId)).thenReturn(mockLoan);
        Loan result = loanService.retrieveLoanByLoanId(loanId);

        assertNotNull(result, "Loan should be returned");
        assertEquals(mockLoan, result, "The returned loan should match the mock loan");

        verify(mockLoanRepository).retrieveLoanByLoanId(loanId);
    }


    @Test
    void retrieveActiveLoansByMembershipId_shouldReturnLoans_whenLoansExist() {
        long membershipId = 1L;
        Loan loan = new Loan();
        when(mockLoanRepository.retrieveActiveLoansByMembershipId(membershipId))
                .thenReturn(List.of(loan));

        List<Loan> loans = loanService.retrieveActiveLoansByMembershipId(membershipId);

        assertNotNull(loans);
        assertEquals(1, loans.size());
        assertEquals(loan, loans.getFirst());
    }

    @Test
    void retrieveActiveLoansByMembershipId_shouldThrowException_whenNoLoansExist() {
        long membershipId = 1L;
        when(mockLoanRepository.retrieveActiveLoansByMembershipId(membershipId))
                .thenReturn(Collections.emptyList());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> loanService.retrieveActiveLoansByMembershipId(membershipId)
        );

        assertEquals("Membership with ID" + membershipId + " not found.", exception.getMessage());
    }


    @Test
    void convertReservationToLoan_shouldCallMarkReservationAsLoaned() {
        Reservation reservation = new Reservation();
        reservation.setReservationId(1L);

        when(mockLoanRepository.store(any(Loan.class))).thenAnswer(invocation -> invocation.getArgument(0));

        loanService.convertReservationToLoan(reservation);

        verify(mockReservationService).markReservationAsLoaned(reservation.getReservationId());
    }


    @Test
    void returnToCatalog_ShouldThrowException_WhenProductCopyDoesNotExist() {
        // Arrange
        long productCopyId = 1L;
        when(mockProductRepository.retrieveProductCopyById(productCopyId)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class,
                () -> loanService.returnToCatalog(productCopyId),
                "Expected returnToCatalog to throw, but it didn't");

        assertEquals("ProductCopy not found with ID: " + productCopyId, thrown.getMessage());
        verify(mockProductRepository, never()).updateProductCopy(any()); // Ensure update is not called
    }
}