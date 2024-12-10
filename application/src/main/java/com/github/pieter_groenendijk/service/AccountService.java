package com.github.pieter_groenendijk.service;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.exception.EntityNotFoundException;


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
        return accountRepository.store(account);
    }
}
