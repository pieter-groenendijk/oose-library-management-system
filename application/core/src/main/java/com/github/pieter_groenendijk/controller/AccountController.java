package com.github.pieter_groenendijk.controller;

import com.github.pieter_groenendijk.exception.EntityNotFoundException;
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

import java.util.NoSuchElementException;

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
    public Account retrieveAccountById(@PathVariable("id") long id) throws Exception {
        try {
            return accountService.retrieveAccountById(id);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("");
        }
    }

    @Operation(summary = "Create an account", description = "Add a new account to the database")
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountRequestDTO account) throws Exception {
        accountService.store(account);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update an account", description = "Update an account in the database")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable("id") long id, @RequestBody AccountRequestDTO account) throws Exception {
        accountService.update(id, account);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Set account blocked", description = "Set an account from blocked to unblocked and back")
    @PostMapping("/setBlocked/{id}/{blocked}")
    public ResponseEntity<?> setAccountBlocked(@PathVariable("id") long id, @PathVariable boolean newValue) throws Exception {
        accountService.setIsBlocked(id, newValue);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Softdelete an account", description = "Softdelete an account in the database")
    @PutMapping("/softdelete/{id}")
    public ResponseEntity<?> softDeleteAccount(@PathVariable("id") long id) throws Exception {
        accountService.softDeleteAccount(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
