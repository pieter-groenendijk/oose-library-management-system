package com.github.pieter_groenendijk.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import com.github.pieter_groenendijk.service.AccountService;
import com.github.pieter_groenendijk.repository.AccountRepository;
import com.github.pieter_groenendijk.model.Account;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import org.hibernate.SessionFactory;

@RestController
@RequestMapping("/account") 
public class AccountController {

    private AccountService accountService;
    private SessionFactory sessionFactory = new SessionFactoryFactory().create();

    private AccountController()
    {
        AccountRepository accountRepository = new AccountRepository(sessionFactory);
        accountService = new AccountService(accountRepository);
    }

    @Operation(summary = "Retrieve an account", description = "Retrieve an account by Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Account found"),
        @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/{id}")
    public Account retrieveAccountById(@PathVariable("id") long id) {
        Account account = accountService.retrieveById(id);
        return account;
    }

    @Operation(summary = "Create an account", description = "Add a new account to the database")
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.storeAccount(account);
    }
}