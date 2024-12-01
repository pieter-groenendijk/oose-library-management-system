package com.github.pieter_groenendijk;

import com.github.pieter_groenendijk.hibernate.SessionFactoryFactory;
import com.github.pieter_groenendijk.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.UUID;

public class HibernateTest {
    public static void main(String[] args) {
        // Create the SessionFactory
        SessionFactory sessionFactory = new SessionFactoryFactory().create();
        System.out.println("Test");

        // Open a new session
        Session session = sessionFactory.openSession();

        try {
            // Begin the transaction
            session.beginTransaction();

            // Generate a unique email
            String newEmail = UUID.randomUUID().toString();

            // Creating a new Account
            Account account = new Account();
            account.setEmail(newEmail + "@example.com");
            account.setFirstName("Henk");
            account.setLastName("De Vries");
            account.setDateOfBirth(new Date());
            account.setGender('M');
            account.setActive(true);

            // Save the account to the session
            session.save(account);
            System.out.println(account.toString());
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
            sessionFactory.close();
        }
    }
}
