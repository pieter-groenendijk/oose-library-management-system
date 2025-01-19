package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.repository.fine.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Optional;
import org.hibernate.HibernateException;
@org.springframework.stereotype.Repository
public class AccountRepository extends Repository implements IAccountRepository {
    public AccountRepository (SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<Account> retrieveAccountById(long id) throws Exception {
        return super.get(Account.class, id);
    }

    @Override
    public void store(Account account) throws Exception {
        super.persist(account);
    }

    public boolean doesAccountExistByEmail(String email) {
        Session session = this.SESSION_FACTORY.openSession();
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

//    public void store(Account account) {
//        Session session = super.SESSION_FACTORY.openSession();
//
//        try {
//            session.beginTransaction();
//            session.persist(account);
//            session.flush();
//
//            session.getTransaction().commit();
//
//        } catch (HibernateException e) {
//            if (session.getTransaction() != null) {
//                session.getTransaction().rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }

    public void update(Account account) {
        Session session = super.SESSION_FACTORY.openSession();

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
    }

    @Override
    public Account blockAccount(Account account) throws Exception {
        account.setBlocked(true);
        super.merge(account);

        return account;
    }

    @Override
    public Optional<Account> retrieveAccountFromLoan(Loan loan) throws Exception {
        return super.performAtomicOperationReturning((session -> {
            Query<Account> query = session.createQuery(
                """
                select 
                    Account a 
                from 
                    Loan l 
                inner join 
                    Membership m 
                on 
                    l.membership = m 
                where
                    l = :loan
                """,
                Account.class
            );

            query.setParameter("loan", loan);

            return Optional.ofNullable(
                query.getSingleResultOrNull()
            );
        }));
    }
}
