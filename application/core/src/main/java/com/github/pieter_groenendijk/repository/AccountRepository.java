package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.repository.fine.Repository;
import org.hibernate.SessionFactory;
import java.util.Optional;

public class AccountRepository extends Repository implements IAccountRepository {
    public AccountRepository (SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Account> retrieveAccountById(long id) throws Exception {
        return super.get(Account.class, id);
    }

    public Account store(Account account) throws Exception {
        super.persist(account);

        return account;
    }

    public Account deleteAccountById(long id) throws Exception {
        Account account = super.get(Account.class, id).orElseThrow();

        super.remove(account);

        return account;
    }

    @Override
    public Account blockAccount(Account account) throws Exception {
        account.setBlocked(true);
        super.merge(account);

        return account;
    }
}
