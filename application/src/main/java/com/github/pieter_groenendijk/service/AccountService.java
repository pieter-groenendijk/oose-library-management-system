package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.exception.*;
import com.github.pieter_groenendijk.validator.*;
import java.time.LocalDate;

public class AccountService {

    private IAccountRepository accountRepository;

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account retrieveById(long id) {
    return accountRepository.retrieveById(id)
            .orElseThrow(() -> new EntityNotFoundException("Account with ID " + id + " not found."));
}

    public Account storeAccount(Account account) {
        if (inputIsValid(Account account))
        {
            return accountRepository.store(account);
        } 
    }

    private boolean inputIsValid(Account account)
    {
        if (!EmailValidator.IsValidEmail(account.getEmail()))
        {
            throw new InputValidationExpection("Email " + account.getEmail() + "is not valid");
        } 
        else if (!GenderCheck.exists(account.getGender())
        {
            throw new InputValidationExpection("Unknown gender identifier: " + account.getGender());
        }
        else if (!account.getDateOfBirth().isBefore.LocalDate.now())
        {
            throw new InputValidationExpection("Date of birth " + account.getDateOfBirth().toString() + " is in the future");
        }
        return true;
    }
}
