package com.github.pieter_groenendijk.repository;

import java.util.Date;
import java.util.UUID;
import com.github.pieter_groenendijk.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;

public class AccountRepository implements IAccountRepository {

    private SessionFactory sessionFactory;

    public AccountRepository() {
        sessionFactory = new SessionFactoryFactory().create();
        System.out.println("Repo1");
    }

    public Account retrieveById(long id) {
        Session session = sessionFactory.openSession();

        Account account = null;
        try {
            session.beginTransaction();
            long accountId = id;
            account = session.get(Account.class, accountId);

            if (account != null) {
                System.out.println("Account found: " + account.getEmail());
            } else {
                System.out.println("No account found with ID: " + accountId);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("Data retrieval failed!");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return account;
    }

    public Account store(Account account) {
        Session session = sessionFactory.openSession();

        try {
            // Begin the transaction
            session.beginTransaction();

//            // Creating a new Account
//            Account account = new Account();
//            account.setEmail(newEmail + "@example.com");
//            account.setFirstName("Henk");
//            account.setLastName("De Vries");
//            account.setDateOfBirth(new Date());
//            account.setGender('M');
//            account.setActive(true);

            // Save the account to the session
            session.save(account);
//            System.out.println(account.toString());
            // Ensure the data is written to the database by flushing the session
            session.flush();

            // Commit the transaction
            session.getTransaction().commit();

            System.out.println("Data inserted successfully!");
        } catch (Exception e) {
            // If an error occurs, rollback the transaction
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("Data-insert failed!");
            // Print the stack trace for debugging
            e.printStackTrace();
        } finally {
            // Close the session and session factory
            session.close();
//            sessionFactory.close();
        }
        return account;
    }
}
