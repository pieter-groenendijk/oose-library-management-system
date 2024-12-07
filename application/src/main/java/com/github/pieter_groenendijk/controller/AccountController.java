package com.github.pieter_groenendijk.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import com.github.pieter_groenendijk.service.AccountService;
import com.github.pieter_groenendijk.model.Account;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/account") // Base URL path
public class AccountController {

    private AccountService accountService;

    public AccountController() {
        accountService = new AccountService();
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