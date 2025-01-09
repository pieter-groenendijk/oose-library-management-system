package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Account;
import java.util.Optional;

public interface IAccountRepository {
    Optional<Account> retrieveAccountById(long id) throws Exception;
    Account store(Account account) throws Exception;
    Account deleteAccountById(long id) throws Exception;
    Account blockAccount(Account account) throws Exception;
}