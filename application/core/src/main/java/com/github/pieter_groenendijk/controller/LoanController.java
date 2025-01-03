package com.github.pieter_groenendijk.controller;

import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.model.DTO.ExtendLoanDTO;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.repository.*;
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

    private final ILoanService loanService;
    private final SessionFactory sessionFactory = new SessionFactoryFactory().create();


    public LoanController() {
        ILoanRepository loanRepository = new LoanRepository(sessionFactory);
        IProductRepository productRepository = new ProductRepository(sessionFactory);
        this.loanService = new LoanService(loanRepository);
    }

    @Operation(summary = "Create a Loan", description = "Create a new Loan")
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
        return loanService.retrieveLoanByLoanId(loanId);
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

    @Operation(summary = "Extend a loan", description = "Extend a loan by loanId")
    @PutMapping("/{loanId}")
    public Loan extendLoan(@PathVariable("loanId") long loanId, @RequestBody ExtendLoanDTO extendLoanDTO) {
        return loanService.extendLoan(loanId, extendLoanDTO.getReturnBy());
    }
}






