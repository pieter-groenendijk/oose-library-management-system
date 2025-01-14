package com.github.pieter_groenendijk.service.loan;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.LoanStatus;
import com.github.pieter_groenendijk.repository.ILoanRepository;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import com.github.pieter_groenendijk.service.IReservationService;
import com.github.pieter_groenendijk.service.loan.event.ILoanEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private Loan mockLoan;

    @BeforeEach
    public void setUp() {
        mockLoanRepository = mock(ILoanRepository.class);
        mockEventService = mock(ILoanEventService.class);
        loanService = new LoanService(mockLoanRepository, mockMembershipRepository,mockEventService, mockReservationService);
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
    void retrieveLoanByLoanId() {
        long loanId = 123L;
        Loan mockLoan = new Loan();
        when(mockLoanRepository.retrieveLoanByLoanId(loanId)).thenReturn(mockLoan);
        Loan result = loanService.retrieveLoanByLoanId(loanId);

        assertNotNull(result, "Loan should be returned");
        assertEquals(mockLoan, result, "The returned loan should match the mock loan");

        verify(mockLoanRepository).retrieveLoanByLoanId(loanId);
    }

}