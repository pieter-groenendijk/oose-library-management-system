package com.github.pieter_groenendijk.service;

import java.util.Date;
import java.util.UUID;
import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.repository.AccountRepository;
import com.github.pieter_groenendijk.repository.IAccountRepository;

public class AccountService {

    private IAccountRepository _accountRepository;

    public AccountService() {
        _accountRepository = new AccountRepository();
        System.out.println("Serv 1");
    }

    public Account retrieveById(long id){
        return _accountRepository.retrieveById(id);
    }

    public Account storeAccount(Account account) {
        return _accountRepository.store(account);
    }
}
