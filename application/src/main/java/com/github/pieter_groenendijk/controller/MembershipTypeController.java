package com.github.pieter_groenendijk.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import com.github.pieter_groenendijk.service.AccountService;
import com.github.pieter_groenendijk.repository.MembershipTypeRepository;
import com.github.pieter_groenendijk.repository.AccountRepository;
import com.github.pieter_groenendijk.model.MembershipType;
import com.github.pieter_groenendijk.model.Account;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import org.hibernate.SessionFactory;

@RestController
@RequestMapping("/membershipType") 
public class MembershipTypeController {

    private AccountService accountService;
    private SessionFactory sessionFactory = new SessionFactoryFactory().create();

    private MembershipTypeController()
    {
        AccountRepository accountRepository = new AccountRepository(sessionFactory);
        MembershipTypeRepository membershipTypeRepository = new MembershipTypeRepository(sessionFactory);
        accountService = new AccountService(accountRepository, membershipTypeRepository);
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
    public MembershipType createMembershipType(@RequestBody MembershipType membershipType) {
        return accountService.store(membershipType);
    }
}