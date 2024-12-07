package com.github.pieter_groenendijk.repository;

import java.util.Date;
import java.util.UUID;
import com.github.pieter_groenendijk.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import java.util.Optional;

public class AccountRepository implements IAccountRepository {

    private SessionFactory sessionFactory;

    public AccountRepository (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Account> retrieveById(long id) {
        Session session = sessionFactory.openSession();
        Account account;

        try {
            account = session.get(Account.class, id);
        } finally {
            session.close();
        }
        return Optional.ofNullable(account);
    }

    public Account store(Account account) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.persist(account);
            session.flush();

            session.getTransaction().commit();

        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return account;
    }
}
