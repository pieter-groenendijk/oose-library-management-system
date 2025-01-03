package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Account;
import java.util.Optional;

public interface IAccountRepository {
    Optional<Account> retrieveAccountById(long id);
    Account store(Account account);
    Optional<Account> deleteAccountById(long id);
}