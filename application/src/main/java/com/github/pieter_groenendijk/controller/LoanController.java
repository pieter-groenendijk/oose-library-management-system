package com.github.pieter_groenendijk.controller;

import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.repository.LoanRepository;
import com.github.pieter_groenendijk.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.SessionFactory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private SessionFactory sessionFactory = new SessionFactoryFactory().create();
    LoanRepository loanRepository = new LoanRepository(sessionFactory);
    LoanService loanService = new LoanService(loanRepository);
    public LoanController() {}

    @Operation(summary = "Retrieve a loan", description = "Retrieve a loan by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/{loanId}")
    public Loan retrieveLoanByLoanId(@PathVariable("loanId") long loanId) {
        return loanService.retrieveLoanByLoanId(loanId);

    }

    @Operation(summary = "Retrieve loans for member", description = "Retrieve loans for Member")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/{id}")
    public List<Loan> retrieveLoanByMembershipId(@PathVariable("membershipId") long membershipId) {
        return loanService.retrieveLoanByMembershipId(membershipId);
    }

    @Operation(summary = "Create a loan", description = "Add a new loan to the database")
    @PostMapping
    public Loan createLoan(@RequestBody Loan loan) {
        return loanService.store(loan);
    }
}





