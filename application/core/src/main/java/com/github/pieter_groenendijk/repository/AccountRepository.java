package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Optional;

public class AccountRepository implements IAccountRepository {

    private SessionFactory sessionFactory;

    public AccountRepository (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Account> retrieveAccountById(long id) {
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

    public Optional<Account> deleteAccountById(long id) {
        Session session = sessionFactory.openSession();
        Account account;

        try {
            session.beginTransaction();
            account = session.get(Account.class, id);
            if (account != null) {
                session.delete(account);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
             if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Optional.ofNullable(account);
    }
}
