package com.github.pieter_groenendijk.controller;

import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.repository.LoanRepository;
import com.github.pieter_groenendijk.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.SessionFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private SessionFactory sessionFactory = new SessionFactoryFactory().create();
    LoanRepository loanRepository = new LoanRepository(sessionFactory);
    LoanService loanService = new LoanService(loanRepository);
    public LoanController() {}


    @Operation(summary = "Create a Loan", description = "Create a new Loan")
    public ResponseEntity<Loan> store(@RequestParam long membershipId, @RequestParam long copyId) {
        Loan loan = loanService.store(membershipId, copyId);
        return new ResponseEntity<>(loan, HttpStatus.CREATED);
    }



    @Operation(summary = "Retrieve a loan", description = "Retrieve a loan by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan found"),
            @ApiResponse(responseCode = "404", description = "Loan not found")
    })
    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> retrieveLoanByLoanId(@PathVariable("loanId") long loanId) {
        Loan loan = loanService.retrieveLoanByLoanId(loanId);
        if (loan != null) {
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Retrieve loans for member", description = "Retrieve loans for Member")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/member/{membershipId}")
    public ResponseEntity<List<Loan>> retrieveLoanByMembershipId(@PathVariable("membershipId") long membershipId) {
        List<Loan> loans = loanService.retrieveLoanByMembershipId(membershipId);
        if (!loans.isEmpty()) {
            return new ResponseEntity<>(loans, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}






