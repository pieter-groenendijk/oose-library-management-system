package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Optional;
import org.hibernate.HibernateException;

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

    public boolean doesAccountExistByEmail(String email) {
        Session session = sessionFactory.openSession();
        Account account;

        try {
            String hql = "FROM Account a WHERE a.email = :email";
            account = session.createQuery(hql, Account.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } finally {
            session.close();
        }

        return account != null;
    }

    public void store(Account account) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.persist(account);
            session.flush();

            session.getTransaction().commit();

        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Account update(Account account) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.merge(account);
            session.flush();

            session.getTransaction().commit();

        } catch (HibernateException e) {
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
