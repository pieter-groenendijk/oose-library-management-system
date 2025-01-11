package com.github.pieter_groenendijk.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import com.github.pieter_groenendijk.service.AccountService;
import com.github.pieter_groenendijk.service.IAccountService;
import com.github.pieter_groenendijk.repository.AccountRepository;
import com.github.pieter_groenendijk.repository.MembershipTypeRepository;
import com.github.pieter_groenendijk.repository.MembershipRepository;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.repository.IMembershipTypeRepository;
import com.github.pieter_groenendijk.repository.IMembershipRepository;
import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.DTO.AccountRequestDTO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import org.hibernate.SessionFactory;

@RestController
@RequestMapping("/account") 
public class AccountController {

    private IAccountService accountService;
    private SessionFactory sessionFactory = new SessionFactoryFactory().create();

    private AccountController()
    {
        IAccountRepository accountRepository = new AccountRepository(sessionFactory);
        IMembershipTypeRepository membershipTypeRepository = new MembershipTypeRepository(sessionFactory);
        IMembershipRepository membershipRepository = new MembershipRepository(sessionFactory);
        accountService = new AccountService(accountRepository, membershipTypeRepository, membershipRepository);
    }

    @Operation(summary = "Retrieve an account", description = "Retrieve an account by Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Account found"),
        @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/{id}")
    public Account retrieveAccountById(@PathVariable("id") long id) {
        return accountService.retrieveAccountById(id);
    }

    @Operation(summary = "Create an account", description = "Add a new account to the database")
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountRequestDTO account) {
        accountService.store(account);
        return ResponseEntity.status(HttpStatus.OK);
    }

    @Operation(summary = "Update an account", description = "Update an account in the database")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable("id") long id, @RequestBody AccountRequestDTO account){
        accountService.update(id, account);
        return ResponseEntity.status(HttpStatus.OK);
    }

    @Operation(summary = "Toggle account active", description = "Toggle an account from active to inactive and back")
    @PostMapping("/toggleActive/{id}")
    public ResponseEntity<?> toggleAccountActive(@PathVariable("id") long id, @RequestBody boolean newValue) {
        accountService.toggleIsActive(id, newValue);
        return ResponseEntity.status(HttpStatus.OK);
    }
}
