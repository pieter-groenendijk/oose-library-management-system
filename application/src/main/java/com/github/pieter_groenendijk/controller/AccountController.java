package com.github.pieter_groenendijk.controller; // Adjust package as needed

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import com.github.pieter_groenendijk.service.AccountService;
import com.github.pieter_groenendijk.model.Account;

@RestController
@RequestMapping("/account") // Base URL path
public class AccountController {

    private AccountService _accountService;

    public AccountController() {
        System.out.println("Contr1");
        _accountService = new AccountService();
    }

    @Operation(summary = "Retrieve an account", description = "Retrieve an account by Id")
    @GetMapping("/{id}")
    public Account retrieveAccountById(@PathVariable("id") long id) {
        Account account = _accountService.retrieveById(id);
        return account;
    }

    @Operation(summary = "Create an account", description = "Add a new account to the database")
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return _accountService.storeAccount(account);
    }
}