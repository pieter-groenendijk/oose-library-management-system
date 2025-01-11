package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Account;
import java.util.Optional;

public interface IAccountRepository {
    Optional<Account> retrieveAccountById(long id);
    void store(Account account);
    void update(Account account);
    boolean doesAccountExistByEmail(String email);
}