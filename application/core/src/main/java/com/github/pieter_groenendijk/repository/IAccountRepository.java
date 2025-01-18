package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.Loan;

import java.util.Optional;

public interface IAccountRepository {
    Optional<Account> retrieveAccountById(long id) throws Exception;
    Account store(Account account) throws Exception;
    Account update(Account account);
    boolean doesAccountExistByEmail(String email);
    Account blockAccount(Account account) throws Exception;
    Optional<Account> retrieveAccountFromLoan(Loan loan) throws Exception;
}