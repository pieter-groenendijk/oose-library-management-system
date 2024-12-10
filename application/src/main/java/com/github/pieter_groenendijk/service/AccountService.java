package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import com.github.pieter_groenendijk.exception.InputValidationException;
import com.github.pieter_groenendijk.service.validator.EmailValidator;
import com.github.pieter_groenendijk.service.validator.GenderCheck;
import java.time.LocalDate;
import java.time.ZoneId;

public class AccountService {

    private final IAccountRepository accountRepository;

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account retrieveById(long id) {
        return accountRepository.retrieveById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account with ID " + id + " not found."));
    }

    public Account storeAccount(Account account) {
        if (inputIsValid(account)) {
            return accountRepository.store(account);
        }
        throw new InputValidationException("Account input is not valid");
    }

    private boolean inputIsValid(Account account) {
        if (!EmailValidator.isValidEmail(account.getEmail())) {
            throw new InputValidationException("Email " + account.getEmail() + " is not valid");
        } else if (!GenderCheck.exists(account.getGender())) {
            throw new InputValidationException("Unknown gender identifier: " + account.getGender());
        } else if (!account.getDateOfBirth().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .isBefore(LocalDate.now())) {
            throw new InputValidationException("Date of birth " + account.getDateOfBirth() + " is in the future");
        }
        return true;
    }
}