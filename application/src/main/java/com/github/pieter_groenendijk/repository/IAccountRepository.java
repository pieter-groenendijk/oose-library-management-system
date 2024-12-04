package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Account;

public interface IAccountRepository {
    Account retrieveById(long id);
    Account store(Account account);
}