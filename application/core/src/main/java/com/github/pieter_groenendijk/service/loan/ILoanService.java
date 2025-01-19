package com.github.pieter_groenendijk.service.loan;

import com.github.pieter_groenendijk.model.DTO.LoanRequestDTO;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.model.Reservation;

import java.time.LocalDate;
import java.util.List;

import static com.github.pieter_groenendijk.service.ServiceUtils.LOAN_LENGTH;


public interface ILoanService {
    Loan store(LoanRequestDTO loan) throws Exception;

    void extendLoan(long loanId, LocalDate returnBy);

    public LocalDate generateReturnByDate(LocalDate returnBy);

    void returnToCatalog(long productCopyId);
    void returnLoan(long loanId);

    void handleOverdueLoans();

    boolean checkIsLate(Loan loan);

    void updateLoanStatusIfNeeded(Loan loan);

    Loan retrieveLoanByLoanId(long loanId);

    List<Loan> retrieveActiveLoansByMembershipId(long membershipId);

    Loan convertReservationToLoan(Reservation reservation);

    void validateLoanRequestDTO(LoanRequestDTO loanRequestDTO);
}
