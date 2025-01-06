package com.github.pieter_groenendijk.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import com.github.pieter_groenendijk.service.AccountService;
import com.github.pieter_groenendijk.service.IAccountService;
import com.github.pieter_groenendijk.repository.MembershipTypeRepository;
import com.github.pieter_groenendijk.repository.IMembershipTypeRepository;
import com.github.pieter_groenendijk.repository.AccountRepository;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.model.MembershipType;
import com.github.pieter_groenendijk.model.Account;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import org.hibernate.SessionFactory;
import com.github.pieter_groenendijk.model.Membership;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import com.github.pieter_groenendijk.repository.MembershipRepository;
import com.github.pieter_groenendijk.model.DTO.MembershipTypeRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/membershipType") 
public class MembershipTypeController {

    private IAccountService accountService;
    private SessionFactory sessionFactory = new SessionFactoryFactory().create();

    private MembershipTypeController()
    {
        IAccountRepository accountRepository = new AccountRepository(sessionFactory);
        IMembershipTypeRepository membershipTypeRepository = new MembershipTypeRepository(sessionFactory);
        IMembershipRepository membershipRepository = new MembershipRepository(sessionFactory);
        accountService = new AccountService(accountRepository, membershipTypeRepository, membershipRepository);
    }

    @Operation(summary = "Retrieve a membershipType", description = "Retrieve a membershipType by Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "MembershipType found"),
        @ApiResponse(responseCode = "404", description = "MembershipType not found")
    })
    @GetMapping("/{id}")
    public MembershipType retrieveMembershipTypeById(@PathVariable("id") long id) {
        MembershipType membershipType = accountService.retrieveMembershipTypeById(id);
        return membershipType;
    }

    @Operation(summary = "Create a membershipType", description = "Add a new membershipType to the database")
    @PostMapping
    public ResponseEntity<?> createMembershipType(@RequestBody MembershipTypeRequestDTO membershipType) {
        accountService.store(membershipType);
        return ResponseEntity.status(HttpStatus.CREATED).body("Succes!");
    }

//    @Operation(summary = "Update a membershipType", description = "Change a membershipType ")
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateAccount(@PathVariable("id") long id, @RequestBody)
}