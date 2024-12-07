package com.github.pieter_groenendijk.service;

import java.util.Date;
import java.util.UUID;
import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.repository.AccountRepository;
import com.github.pieter_groenendijk.repository.IAccountRepository;
import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import org.hibernate.SessionFactory;
import com.github.pieter_groenendijk.exception.EntityNotFoundException;
import java.util.Optional;

public class AccountService {

    private IAccountRepository accountRepository;
    private SessionFactory sessionFactory = new SessionFactoryFactory().create();

    public AccountService() {
        accountRepository = new AccountRepository(sessionFactory);
    }

    public Account retrieveById(long id) {
    return accountRepository.retrieveById(id)
            .orElseThrow(() -> new EntityNotFoundException("Account with ID " + id + " not found."));
}

    public Account storeAccount(Account account) {
        return accountRepository.store(account);
    }
}
