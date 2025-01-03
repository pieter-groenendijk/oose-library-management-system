package com.github.pieter_groenendijk.controller;

import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.repository.ILoanRepository;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import com.github.pieter_groenendijk.repository.LoanRepository;
import com.github.pieter_groenendijk.repository.MembershipRepository;
import com.github.pieter_groenendijk.service.ILoanService;
import com.github.pieter_groenendijk.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.SessionFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final SessionFactory sessionFactory = new SessionFactoryFactory().create();
    private final ILoanService loanService;

    public LoanController() {
        ILoanRepository loanRepository = new LoanRepository(sessionFactory);
        this.loanService = new LoanService(loanRepository);
    }

    @Operation(summary = "Create a Loan", description = "Create a new Loan")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Loan created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Membership or Product not found")
    })
    @PostMapping
    public Loan store(@RequestBody Loan loan) {
      return loanService.store(loan);
    }

    @Operation(summary = "Retrieve a loan", description = "Retrieve a loan by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan found"),
            @ApiResponse(responseCode = "204", description = "No loan found for the given loanId\"")
    })
    @GetMapping("/{loanId}")
    public Loan retrieveLoanByLoanId(@PathVariable("loanId") long loanId) {
        Loan loan = loanService.retrieveLoanByLoanId(loanId);
            return loan;
    }

    @Operation(summary = "Retrieve all loans for a membership", description = "Retrieve loans by membershipId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan(s) for MembershipId found"),
            @ApiResponse(responseCode = "204", description = "No loans found for the given membershipId\"")
    })
    @GetMapping("/member/{membershipId}")
    public ResponseEntity<List<Loan>> retrieveActiveLoansByMembershipId(@Parameter(description = "ID of the membership to retrieve loans for", required = true)
                                                                        @PathVariable("membershipId") long membershipId) {
        List<Loan> loans = loanService.retrieveActiveLoansByMembershipId(membershipId);
        if (!loans.isEmpty()) {
            return new ResponseEntity<>(loans, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
    }
}






